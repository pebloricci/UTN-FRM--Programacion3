package com.practico.jpa;

import com.practico.jpa.entidades.*;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("clinicaPU");
        EntityManager em = emf.createEntityManager();
        try {
            persistirDatos(em);
            consultas(em);
        } finally {
            em.close();
            emf.close();
        }
    }

    // ---------------------------------------------------------------
    // PARTE 1: persistir los datos
    // ---------------------------------------------------------------
    private static void persistirDatos(EntityManager em) {
        em.getTransaction().begin();

        // 3 medicamentos
        Medicamento ibuprofeno = new Medicamento("Ibupirac", "Ibuprofeno", 1);
        Medicamento amoxicilina = new Medicamento("Amoxidal", "Amoxicilina", 1);
        Medicamento paracetamol = new Medicamento("Tafirol", "Paracetamol", 1);
        em.persist(ibuprofeno);
        em.persist(amoxicilina);
        em.persist(paracetamol);

        // 2 médicos
        Medico garcia = new Medico("Laura", "García", 50, "Clínica médica", "MP-1234");
        Medico lopez = new Medico("Martín", "López", 42, "Cardiología", "MP-5678");
        em.persist(garcia);
        em.persist(lopez);

        // 3 pacientes
        Paciente p1 = new Paciente("Juan", "Pérez", 45, 30111222, "OSDE", LocalDate.of(1981, 5, 12), 'M');
        Paciente p2 = new Paciente("Ana", "Gómez", 28, 40222333, "Swiss Medical", LocalDate.of(1998, 8, 3), 'F');
        Paciente p3 = new Paciente("Carlos", "Díaz", 62, 20333444, "OSDE", LocalDate.of(1964, 1, 25), 'M');

        // Medicamentos asociados a cada paciente (N a N)
        p1.getMedicamentos().add(ibuprofeno);
        p1.getMedicamentos().add(paracetamol);
        p2.getMedicamentos().add(amoxicilina);
        p3.getMedicamentos().add(paracetamol);

        em.persist(p1);
        em.persist(p2);
        em.persist(p3);

        // 2 historias clínicas (la FK está en HistoriaClinica)
        em.persist(new HistoriaClinica("Hipertensión controlada, sin alergias conocidas", p1));
        em.persist(new HistoriaClinica("Cuadros de bronquitis recurrente", p2));

        // 4 consultas con fechas y diagnósticos distintos
        Consulta c1 = new Consulta(LocalDate.of(2026, 3, 10), "Hipertensión arterial");
        Consulta c2 = new Consulta(LocalDate.of(2026, 4, 2), "Bronquitis aguda");
        Consulta c3 = new Consulta(LocalDate.of(2026, 5, 15), "Control de diabetes");
        Consulta c4 = new Consulta(LocalDate.of(2026, 6, 20), "Arritmia leve");
        em.persist(c1);
        em.persist(c2);
        em.persist(c3);
        em.persist(c4);

        // Al ser unidireccional, se agregan a la lista del paciente y a la del médico
        p1.getConsultas().add(c1);  lopez.getConsultas().add(c1);
        p2.getConsultas().add(c2);  garcia.getConsultas().add(c2);
        p3.getConsultas().add(c3);  garcia.getConsultas().add(c3);
        p1.getConsultas().add(c4);  lopez.getConsultas().add(c4);

        em.getTransaction().commit();
        em.clear(); // para que las consultas vayan realmente a la BD
        System.out.println("\n=== Datos persistidos correctamente ===\n");
    }

    // ---------------------------------------------------------------
    // PARTE 2: consultas JPQL
    // ---------------------------------------------------------------
    private static void consultas(EntityManager em) {

        // 1. Pacientes mayores de 30 años
        System.out.println("\n--- 1. Pacientes mayores de 30 años ---");
        em.createQuery("SELECT p FROM Paciente p WHERE p.edad > 30", Paciente.class)
                .getResultList()
                .forEach(System.out::println);

        // 2. Consultas realizadas por un médico específico (por matrícula)
        System.out.println("\n--- 2. Consultas del médico MP-1234 ---");
        em.createQuery("SELECT c FROM Medico m JOIN m.consultas c WHERE m.matricula = :mat", Consulta.class)
                .setParameter("mat", "MP-1234")
                .getResultList()
                .forEach(System.out::println);

        // 3. Medicamentos asociados a un paciente (por DNI)
        System.out.println("\n--- 3. Medicamentos del paciente DNI 30111222 ---");
        em.createQuery("SELECT m FROM Paciente p JOIN p.medicamentos m WHERE p.dni = :dni", Medicamento.class)
                .setParameter("dni", 30111222)
                .getResultList()
                .forEach(System.out::println);

        // 4. Consultas con diagnóstico y nombre del paciente
        System.out.println("\n--- 4. Consultas: diagnóstico y paciente ---");
        List<Object[]> diag = em.createQuery(
                "SELECT c.diagnostico, p.nombre, p.apellido FROM Paciente p JOIN p.consultas c",
                Object[].class).getResultList();
        for (Object[] f : diag) {
            System.out.println(f[0] + " -> " + f[1] + " " + f[2]);
        }

        // 5. Promedio de edad de los pacientes
        System.out.println("\n--- 5. Promedio de edad ---");
        Double promedio = em.createQuery("SELECT AVG(p.edad) FROM Paciente p", Double.class)
                .getSingleResult();
        System.out.println("Promedio: " + promedio);

        // 6. Pacientes de una obra social específica
        System.out.println("\n--- 6. Pacientes de OSDE ---");
        em.createQuery("SELECT p FROM Paciente p WHERE p.obraSocial = :os", Paciente.class)
                .setParameter("os", "OSDE")
                .getResultList()
                .forEach(System.out::println);

        // 7. Médicos y cantidad de consultas atendidas
        System.out.println("\n--- 7. Médicos y cantidad de consultas ---");
        List<Object[]> conteo = em.createQuery(
                "SELECT m.nombre, m.apellido, COUNT(c) FROM Medico m LEFT JOIN m.consultas c "
                        + "GROUP BY m.id, m.nombre, m.apellido", Object[].class).getResultList();
        for (Object[] f : conteo) {
            System.out.println(f[0] + " " + f[1] + ": " + f[2] + " consultas");
        }

        // 8. Pacientes con la descripción de su historia clínica
        System.out.println("\n--- 8. Pacientes e historia clínica ---");
        List<Object[]> historias = em.createQuery(
                "SELECT p.nombre, p.apellido, h.descripcion FROM HistoriaClinica h JOIN h.paciente p",
                Object[].class).getResultList();
        for (Object[] f : historias) {
            System.out.println(f[0] + " " + f[1] + ": " + f[2]);
        }
    }
}
