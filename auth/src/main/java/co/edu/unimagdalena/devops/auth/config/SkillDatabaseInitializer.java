package co.edu.unimagdalena.devops.auth.config;

import co.edu.unimagdalena.devops.auth.repository.SkillRepository;
import co.edu.unimagdalena.devops.auth.service.SkillSeedService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SkillDatabaseInitializer {

    private final SkillSeedService skillSeedService;

    @PostConstruct
    public void init() {
        // Blandas
        skillSeedService.seed("Pensamiento creativo", "Blanda");
        skillSeedService.seed("Comunicación asertiva", "Blanda");
        skillSeedService.seed("Gestión emocional", "Blanda");
        skillSeedService.seed("Manejo de conflictos", "Blanda");
        skillSeedService.seed("Empoderamiento personal", "Blanda");
        skillSeedService.seed("Disciplina laboral", "Blanda");
        skillSeedService.seed("Capacidad de análisis", "Blanda");
        skillSeedService.seed("Responsabilidad social", "Blanda");
        skillSeedService.seed("Etica profesional", "Blanda");
        skillSeedService.seed("Honestidad", "Blanda");
        skillSeedService.seed("Tolerancia a la frustración", "Blanda");
        skillSeedService.seed("Aprendizaje continuo", "Blanda");
        skillSeedService.seed("Orientación al servicio", "Blanda");
        skillSeedService.seed("Paciencia", "Blanda");
        skillSeedService.seed("Confianza interpersonal", "Blanda");
        skillSeedService.seed("Cortesía", "Blanda");
        skillSeedService.seed("Pensamiento lógico", "Blanda");
        skillSeedService.seed("Sensibilidad cultural", "Blanda");
        skillSeedService.seed("Autonomía", "Blanda");
        skillSeedService.seed("Capacidad de adaptación", "Blanda");
        skillSeedService.seed("Trabajo bajo presión", "Blanda");
        skillSeedService.seed("Capacidad de escucha", "Blanda");
        skillSeedService.seed("Planeación personal", "Blanda");
        skillSeedService.seed("Gestión del cambio", "Blanda");
        skillSeedService.seed("Toma de iniciativa", "Blanda");
        skillSeedService.seed("Orientación al cliente", "Blanda");
        skillSeedService.seed("Pensamiento positivo", "Blanda");
        skillSeedService.seed("Capacidad de observación", "Blanda");
        skillSeedService.seed("Confidencialidad", "Blanda");
        skillSeedService.seed("Influencia y persuasión", "Blanda");
        skillSeedService.seed("Manejo de prioridades", "Blanda");
        skillSeedService.seed("Pensamiento organizado", "Blanda");
        skillSeedService.seed("Gestión del tiempo personal", "Blanda");
        skillSeedService.seed("Trabajo colaborativo", "Blanda");
        skillSeedService.seed("Sentido de pertenencia", "Blanda");
        skillSeedService.seed("Optimismo", "Blanda");
        skillSeedService.seed("Autocontrol", "Blanda");
        skillSeedService.seed("Capacidad de concentración", "Blanda");
        skillSeedService.seed("Empatía social", "Blanda");
        skillSeedService.seed("Escucha empática", "Blanda");
        skillSeedService.seed("Respeto a la diversidad", "Blanda");
        skillSeedService.seed("Manejo de la frustración", "Blanda");
        skillSeedService.seed("Pensamiento sistémico", "Blanda");
        skillSeedService.seed("Colaboración interdepartamental", "Blanda");
        skillSeedService.seed("Gestión del conflicto", "Blanda");
        skillSeedService.seed("Orientación a resultados", "Blanda");
        skillSeedService.seed("Manejo del cambio organizacional", "Blanda");
        skillSeedService.seed("Tolerancia", "Blanda");
        skillSeedService.seed("Capacidad de negociación", "Blanda");
        skillSeedService.seed("Capacidad de aprendizaje rápido", "Blanda");
        skillSeedService.seed("Motivación personal", "Blanda");
        skillSeedService.seed("Capacidad de liderazgo", "Blanda");
        skillSeedService.seed("Asertividad", "Blanda");
        skillSeedService.seed("Capacidad de autocrítica", "Blanda");
        skillSeedService.seed("Trabajo ético", "Blanda");
        skillSeedService.seed("Desarrollo personal", "Blanda");
        skillSeedService.seed("Pensamiento estratégico personal", "Blanda");
        skillSeedService.seed("Capacidad de mediación", "Blanda");
        skillSeedService.seed("Respeto por las normas", "Blanda");
        skillSeedService.seed("Responsabilidad colectiva", "Blanda");
        skillSeedService.seed("Compromiso organizacional", "Blanda");
        skillSeedService.seed("Solidaridad", "Blanda");

        // Duras
        skillSeedService.seed("Programación en Java", "Dura");
        skillSeedService.seed("Programación en Python", "Dura");
        skillSeedService.seed("SQL", "Dura");
        skillSeedService.seed("Git / Control de versiones", "Dura");
        skillSeedService.seed("Linux", "Dura");
        skillSeedService.seed("Docker", "Dura");
        skillSeedService.seed("Kubernetes", "Dura");
        skillSeedService.seed("HTML / CSS", "Dura");
        skillSeedService.seed("Spring Boot", "Dura");
        skillSeedService.seed("React.js", "Dura");
        skillSeedService.seed("Contabilidad financiera", "Dura");
        skillSeedService.seed("Análisis de estados financieros", "Dura");
        skillSeedService.seed("Gestión de presupuestos", "Dura");
        skillSeedService.seed("Auditoría interna", "Dura");
        skillSeedService.seed("Control de inventarios", "Dura");
        skillSeedService.seed("Planeación financiera", "Dura");
        skillSeedService.seed("Gestión de nómina", "Dura");
        skillSeedService.seed("Tributación básica", "Dura");
        skillSeedService.seed("Evaluación de proyectos", "Dura");
        skillSeedService.seed("Costos y presupuestos", "Dura");
        skillSeedService.seed("Marketing digital", "Dura");
        skillSeedService.seed("Copywriting", "Dura");
        skillSeedService.seed("SEO (posicionamiento en buscadores)", "Dura");
        skillSeedService.seed("Análisis de mercado", "Dura");
        skillSeedService.seed("Branding", "Dura");
        skillSeedService.seed("Relaciones públicas", "Dura");
        skillSeedService.seed("Planificación de campañas publicitarias", "Dura");
        skillSeedService.seed("Email marketing", "Dura");
        skillSeedService.seed("Gestión de redes sociales", "Dura");
        skillSeedService.seed("Atención al cliente", "Dura");
        skillSeedService.seed("Control de calidad", "Dura");
        skillSeedService.seed("Lean Manufacturing", "Dura");
        skillSeedService.seed("Mantenimiento preventivo", "Dura");
        skillSeedService.seed("Logística y distribución", "Dura");
        skillSeedService.seed("Gestión de almacenes", "Dura");
        skillSeedService.seed("Compras y suministros", "Dura");
        skillSeedService.seed("Gestión de proveedores", "Dura");
        skillSeedService.seed("Seguridad industrial", "Dura");
        skillSeedService.seed("Ergonomía laboral", "Dura");
        skillSeedService.seed("Normas ISO 9001", "Dura");
        skillSeedService.seed("Gestión ambiental", "Dura");
        skillSeedService.seed("Manejo de residuos sólidos", "Dura");
        skillSeedService.seed("Evaluación de impacto ambiental", "Dura");
        skillSeedService.seed("Energías renovables", "Dura");
        skillSeedService.seed("Agricultura sostenible", "Dura");
        skillSeedService.seed("Conservación de recursos naturales", "Dura");
        skillSeedService.seed("Manejo de agua potable y saneamiento", "Dura");
        skillSeedService.seed("Auditoría ambiental", "Dura");
        skillSeedService.seed("Control de emisiones", "Dura");
        skillSeedService.seed("Educación ambiental", "Dura");
        skillSeedService.seed("Planificación curricular", "Dura");
        skillSeedService.seed("Evaluación educativa", "Dura");
        skillSeedService.seed("Diseño instruccional", "Dura");
        skillSeedService.seed("Tutoría académica", "Dura");
        skillSeedService.seed("Redacción de material educativo", "Dura");
        skillSeedService.seed("Metodologías activas de aprendizaje", "Dura");
        skillSeedService.seed("Orientación vocacional", "Dura");
        skillSeedService.seed("Mediación pedagógica", "Dura");
        skillSeedService.seed("Didáctica general", "Dura");
        skillSeedService.seed("Gestión escolar", "Dura");
        skillSeedService.seed("Primeros auxilios", "Dura");
        skillSeedService.seed("Promoción de la salud", "Dura");
        skillSeedService.seed("Higiene y seguridad alimentaria", "Dura");
        skillSeedService.seed("Bioseguridad", "Dura");
        skillSeedService.seed("Atención al paciente", "Dura");
        skillSeedService.seed("Registro clínico", "Dura");
        skillSeedService.seed("Control de infecciones", "Dura");
        skillSeedService.seed("Educación sanitaria", "Dura");
        skillSeedService.seed("Nutrición y dietética básica", "Dura");
        skillSeedService.seed("Soporte vital básico", "Dura");

        System.out.println("Sincronización inicial de habilidades completada.");
    }

}
