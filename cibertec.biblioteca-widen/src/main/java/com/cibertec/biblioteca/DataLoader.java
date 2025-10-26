package com.cibertec.biblioteca;

//package com.cibertec.biblioteca.datos;

import com.cibertec.biblioteca.entidades.Libro;
import com.cibertec.biblioteca.entidades.Usuario;
import com.cibertec.biblioteca.repositorio.LibroRepositorio;
import com.cibertec.biblioteca.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepositorio usuarioRepositorio;
    private final LibroRepositorio libroRepositorio;

    @Autowired
    public DataLoader(
            UsuarioRepositorio usuarioRepositorio,
            LibroRepositorio libroRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
        this.libroRepositorio = libroRepositorio;
    }

    @Override
    public void run(String... args) throws Exception {

        if (usuarioRepositorio.count() == 0) {
            // Crear usuarios falsos
            Usuario usuario1 = new Usuario();
            usuario1.setNombre("Juan");
            usuario1.setApellido("Pérez");
            usuario1.setDni("12345678");
            usuario1.setTelefono("987654321");
            usuario1.setDireccion("Calle Falsa 123");
            usuario1.setEmail("juan.perez@example.com");
            usuario1.setPassword("password1");
            usuario1.setRole("BIBLIOTECARIO");

            Usuario usuario2 = new Usuario();
            usuario2.setNombre("Ana");
            usuario2.setApellido("García");
            usuario2.setDni("87654321");
            usuario2.setTelefono("912345678");
            usuario2.setDireccion("Avenida Siempre Viva 742");
            usuario2.setEmail("ana.garcia@example.com");
            usuario2.setPassword("password2");
            usuario2.setRole("BIBLIOTECARIO");

            Usuario usuario3 = new Usuario();
            usuario3.setNombre("Carlos");
            usuario3.setApellido("Rodríguez");
            usuario3.setDni("13579246");
            usuario3.setTelefono("914785236");
            usuario3.setDireccion("Calle Sol 789");
            usuario3.setEmail("carlos.rodriguez@example.com");
            usuario3.setPassword("password3");
            usuario3.setRole("LECTOR");

            Usuario usuario4 = new Usuario();
            usuario4.setNombre("María");
            usuario4.setApellido("Fernández");
            usuario4.setDni("24681357");
            usuario4.setTelefono("918273645");
            usuario4.setDireccion("Avenida Luna 456");
            usuario4.setEmail("maria.fernandez@example.com");
            usuario4.setPassword("password4");
            usuario4.setRole("LECTOR");

            usuarioRepositorio.save(usuario1);
            usuarioRepositorio.save(usuario2);
            usuarioRepositorio.save(usuario3);
            usuarioRepositorio.save(usuario4);
        }

        if (libroRepositorio.count() == 0) {
            // Ejemplo 1
            Libro libro1 = new Libro();
            libro1.setTitulo("Cien Años de Soledad");
            libro1.setTipoMaterial("Libro");
            libro1.setAutor("Gabriel García Márquez");
            libro1.setEditorial("Editorial Sudamericana");
            libro1.setAno_public("1967");
            libro1.setGenero("Realismo mágico");
            libro1.setIsbn("9781234567897");
            libro1.setCant_dispon(5);
            libro1.setEstado_libro("Disponible");
            libro1.setResumen("Cien Años de Soledad es una obra monumental del realismo mágico que narra la épica historia de la familia Buendía a lo largo de siete generaciones en el pueblo ficticio de Macondo. La novela explora temas como la soledad, la historia cíclica, y el destino, entremezclando elementos mágicos con la realidad. A través de un lenguaje rico y vibrante, García Márquez teje una narrativa compleja donde lo sobrenatural coexiste con lo cotidiano, creando un mundo donde el tiempo parece ser una fuerza maleable. El autor captura magistralmente la esencia de la vida latinoamericana, reflejando tanto la belleza como las tragedias de la existencia humana. La historia comienza con José Arcadio Buendía y su esposa Úrsula Iguarán, quienes fundan el pueblo de Macondo. A partir de ahí, la novela sigue a sus descendientes mientras se enfrentan a amores prohibidos, guerras, revoluciones y tragedias familiares. La novela es tanto una crónica de la historia de Macondo como una reflexión profunda sobre la condición humana. El destino trágico de la familia Buendía está sellado desde el principio, y a medida que las generaciones pasan, los personajes se ven atrapados en un ciclo interminable de repetición y sufrimiento. La novela culmina en una revelación apocalíptica que sella el destino de Macondo, subrayando la idea de que la historia es cíclica e inevitable. A través de sus personajes inolvidables y su trama intrincada, Cien Años de Soledad se ha convertido en una de las obras más influyentes de la literatura universal, celebrada por su innovación estilística y su profunda visión de la humanidad.");
            libro1.setCategoria("Literatura Hispanoamericana");
            libroRepositorio.save(libro1);

            // Ejemplo 2
            Libro libro2 = new Libro();
            libro2.setTitulo("El Quijote");
            libro2.setTipoMaterial("Libro");
            libro2.setAutor("Miguel de Cervantes");
            libro2.setEditorial("Francisco de Robles");
            libro2.setAno_public("1605");
            libro2.setGenero("Novela");
            libro2.setIsbn("9788478391186");
            libro2.setCant_dispon(3);
            libro2.setEstado_libro("Disponible");
            libro2.setResumen("El Quijote es una de las novelas más influyentes y conocidas de la literatura universal. Es una obra que narra las aventuras y desventuras de Alonso Quijano, un hidalgo manchego que, obsesionado con las novelas de caballería, decide convertirse en caballero andante bajo el nombre de Don Quijote de la Mancha. Acompañado por su fiel escudero Sancho Panza, Don Quijote se embarca en una serie de aventuras en las que, cegado por su idealismo y locura, confunde la realidad con la fantasía, viendo gigantes donde hay molinos de viento, castillos donde hay posadas, y damas nobles donde hay simples campesinas. A lo largo de la obra, Cervantes explora temas como la locura, el idealismo, la realidad, y la ironía. Don Quijote es una figura trágica y cómica al mismo tiempo, un hombre cuyo idealismo lo lleva a enfrentarse a un mundo que ya no comprende ni comparte sus valores. Sancho Panza, por otro lado, representa el realismo, la sabiduría popular y la lealtad, a pesar de que también se ve arrastrado por las fantasías de su amo. La novela es también una crítica mordaz de la sociedad de la época, y a través de las desventuras de Don Quijote y Sancho, Cervantes ofrece una reflexión sobre la naturaleza de la verdad, la justicia y el honor. La obra, publicada en dos partes en 1605 y 1615, es una rica mezcla de géneros literarios y estilos, que va desde la comedia y la sátira hasta la tragedia y la filosofía. La influencia de El Quijote en la literatura es inmensa, y la figura del caballero de la triste figura se ha convertido en un símbolo universal del idealismo y la lucha contra la adversidad.");
            libro2.setCategoria("Clásicos");
            libroRepositorio.save(libro2);

// Ejemplo 3
            Libro libro3 = new Libro();
            libro3.setTitulo("1984");
            libro3.setTipoMaterial("Libro");
            libro3.setAutor("George Orwell");
            libro3.setEditorial("Secker & Warburg");
            libro3.setAno_public("1949");
            libro3.setGenero("Distopía");
            libro3.setIsbn("9780451524935");
            libro3.setCant_dispon(10);
            libro3.setEstado_libro("Disponible");
            libro3.setResumen("1984 es una novela distópica que presenta una visión oscura y aterradora de un futuro en el que un gobierno totalitario, bajo el control del Partido, vigila y controla todos los aspectos de la vida de sus ciudadanos. El protagonista, Winston Smith, trabaja en el Ministerio de la Verdad, donde su tarea es alterar los registros históricos para que coincidan con la versión oficial del Partido. Sin embargo, Winston comienza a cuestionar la autoridad del Partido y la omnipresencia del Gran Hermano, el líder supremo. A medida que Winston busca la verdad y se enamora de Julia, una compañera de trabajo, se da cuenta de que la rebelión contra el Partido es prácticamente imposible. Orwell explora temas como la vigilancia, la propaganda, la manipulación del lenguaje, y la supresión de la individualidad. La novela es una advertencia sobre los peligros de la tiranía y la pérdida de la libertad, y ha sido interpretada como una crítica de los regímenes totalitarios del siglo XX. El mundo de 1984 está gobernado por el miedo, la desconfianza y la represión, donde incluso los pensamientos disidentes son considerados crímenes. La novela es famosa por su concepto del 'doblepensar', la capacidad de mantener dos creencias contradictorias al mismo tiempo, y por el lema del Partido: 'La guerra es paz. La libertad es esclavitud. La ignorancia es la fuerza'. La obra ha tenido un impacto duradero en la cultura popular y sigue siendo relevante en la discusión sobre la libertad, la privacidad y el poder.");
            libro3.setCategoria("Ficción");
            libroRepositorio.save(libro3);

// Ejemplo 4
            Libro libro4 = new Libro();
            libro4.setTitulo("Orgullo y Prejuicio");
            libro4.setTipoMaterial("Libro");
            libro4.setAutor("Jane Austen");
            libro4.setEditorial("T. Egerton, Whitehall");
            libro4.setAno_public("1813");
            libro4.setGenero("Romance");
            libro4.setIsbn("9780141439518");
            libro4.setCant_dispon(4);
            libro4.setEstado_libro("Disponible");
            libro4.setResumen("Orgullo y Prejuicio es una novela que explora las complejidades de las relaciones sociales y el matrimonio en la Inglaterra del siglo XIX. La historia se centra en Elizabeth Bennet, una joven inteligente y de carácter fuerte, que se enfrenta a las expectativas sociales de su familia y su entorno. A lo largo de la novela, Elizabeth se encuentra con Fitzwilliam Darcy, un hombre rico y orgulloso que inicialmente la desprecia. Sin embargo, a medida que ambos personajes se conocen mejor, comienzan a cuestionar sus prejuicios y malentendidos. Austen utiliza la relación entre Elizabeth y Darcy para explorar temas como el orgullo, la clase social, el matrimonio y el amor verdadero. La novela es conocida por su ironía y su crítica de las normas sociales de la época, así como por su representación vívida de los personajes. A través de los diálogos ingeniosos y las observaciones agudas de Austen, el lector es testigo de la evolución de los personajes y la transformación de sus relaciones. Orgullo y Prejuicio ha sido aclamada por su habilidad para capturar las sutilezas de las interacciones humanas y sigue siendo una de las novelas más queridas y leídas de la literatura inglesa.");
            libro4.setCategoria("Clásicos");
            libroRepositorio.save(libro4);

// Ejemplo 5
            Libro libro5 = new Libro();
            libro5.setTitulo("Matar a un ruiseñor");
            libro5.setTipoMaterial("Libro");
            libro5.setAutor("Harper Lee");
            libro5.setEditorial("J.B. Lippincott & Co.");
            libro5.setAno_public("1960");
            libro5.setGenero("Ficción");
            libro5.setIsbn("9780061120084");
            libro5.setCant_dispon(8);
            libro5.setEstado_libro("Disponible");
            libro5.setResumen("Matar a un ruiseñor es una novela que explora la injusticia racial en el sur de los Estados Unidos a través de los ojos de Scout Finch, una niña que vive en la ciudad ficticia de Maycomb, Alabama, durante la Gran Depresión. La historia se centra en el juicio de Tom Robinson, un hombre negro acusado injustamente de violar a una mujer blanca. El padre de Scout, Atticus Finch, es un abogado respetado que defiende a Tom, enfrentándose a la discriminación y el odio de la comunidad. A través de su experiencia, Scout aprende sobre el racismo, la empatía y la moralidad. La novela es una poderosa crítica de la injusticia racial y un llamado a la comprensión y la tolerancia. Harper Lee crea personajes memorables y una narrativa conmovedora que captura la complejidad de las relaciones humanas y los desafíos éticos. Matar a un ruiseñor ha sido aclamada como una de las grandes novelas americanas, y su mensaje sigue resonando en la sociedad contemporánea. La historia de Scout y su familia es un testimonio de la lucha por la justicia y la igualdad, y la figura de Atticus Finch se ha convertido en un símbolo de integridad y valentía moral.");
            libro5.setCategoria("Ficción Americana");
            libroRepositorio.save(libro5);

// Ejemplo 6
            Libro libro6 = new Libro();
            libro6.setTitulo("El Gran Gatsby");
            libro6.setTipoMaterial("Libro");
            libro6.setAutor("F. Scott Fitzgerald");
            libro6.setEditorial("Charles Scribner's Sons");
            libro6.setAno_public("1925");
            libro6.setGenero("Ficción");
            libro6.setIsbn("9780743273565");
            libro6.setCant_dispon(6);
            libro6.setEstado_libro("Disponible");
            libro6.setResumen("El Gran Gatsby es una novela que explora el sueño americano y la decadencia de la sociedad en la década de 1920. La historia sigue a Jay Gatsby, un misterioso y adinerado hombre que vive en una mansión en Long Island, y su obsesión por Daisy Buchanan, una mujer casada a la que conoció años antes y con la que sueña reencontrarse. A través de los ojos de Nick Carraway, el narrador y vecino de Gatsby, la novela ofrece una visión crítica de la riqueza, la superficialidad y la moralidad de la sociedad de la época. A medida que se desarrolla la trama, se revelan las mentiras y engaños que sustentan el mundo de Gatsby, y la historia culmina en una tragedia que expone la fragilidad de los sueños humanos. Fitzgerald utiliza un estilo lírico y simbólico para explorar temas como el amor, la ambición, la corrupción y la ilusión. El Gran Gatsby ha sido aclamada como una de las grandes obras de la literatura americana, y sigue siendo una reflexión poderosa sobre los límites del sueño americano y la naturaleza destructiva de la obsesión.");
            libro6.setCategoria("Ficción Americana");
            libroRepositorio.save(libro6);

// Ejemplo 7
            Libro libro7 = new Libro();
            libro7.setTitulo("Crimen y Castigo");
            libro7.setTipoMaterial("Libro");
            libro7.setAutor("Fiódor Dostoyevski");
            libro7.setEditorial("The Russian Messenger");
            libro7.setAno_public("1866");
            libro7.setGenero("Filosofía");
            libro7.setIsbn("9780140449136");
            libro7.setCant_dispon(2);
            libro7.setEstado_libro("Disponible");
            libro7.setResumen("Crimen y Castigo es una profunda exploración de la psicología del crimen y la culpa. La novela sigue a Rodión Raskólnikov, un joven estudiante empobrecido que, convencido de que está justificado, asesina a una vieja usurera para robarle su dinero. A medida que la culpa y el remordimiento comienzan a consumirlo, Raskólnikov se enfrenta a un dilema moral que lo lleva al borde de la locura. Dostoyevski utiliza a Raskólnikov como un vehículo para explorar temas filosóficos como el nihilismo, el libre albedrío, y la redención. La novela es un retrato sombrío y fascinante de la lucha interna del protagonista, que refleja las tensiones sociales y espirituales de la Rusia del siglo XIX. A lo largo de la obra, Dostoyevski plantea preguntas fundamentales sobre la naturaleza del bien y el mal, y la capacidad del ser humano para encontrar la redención. La narrativa tensa y absorbente mantiene al lector al borde del asiento, mientras que el desarrollo psicológico de los personajes proporciona una experiencia de lectura rica y profunda. Crimen y Castigo sigue siendo una de las grandes novelas de la literatura mundial, valorada tanto por su análisis psicológico como por su impacto filosófico.");
            libro7.setCategoria("Literatura Rusa");
            libroRepositorio.save(libro7);

// Ejemplo 8
            Libro libro8 = new Libro();
            libro8.setTitulo("En busca del tiempo perdido");
            libro8.setTipoMaterial("Libro");
            libro8.setAutor("Marcel Proust");
            libro8.setEditorial("Grasset");
            libro8.setAno_public("1913");
            libro8.setGenero("Ficción");
            libro8.setIsbn("9782070118276");
            libro8.setCant_dispon(4);
            libro8.setEstado_libro("Disponible");
            libro8.setResumen("En busca del tiempo perdido es una vasta y compleja obra literaria que explora la memoria, el tiempo y la identidad a través de los ojos del narrador, Marcel. La novela, compuesta por siete volúmenes, narra la vida de Marcel desde su infancia hasta la adultez, a medida que reflexiona sobre su pasado y las personas que han influido en su vida. Proust utiliza un estilo introspectivo y detallado para capturar los matices de la experiencia humana, desde las emociones más sutiles hasta las relaciones más complicadas. La novela es conocida por su famosa 'magdalena', un símbolo de la memoria involuntaria, y por su análisis profundo de la sociedad francesa de la época. A través de su exploración del tiempo perdido y recobrado, Proust ofrece una meditación sobre la fugacidad de la vida y la búsqueda de significado. En busca del tiempo perdido ha sido aclamada como una de las obras maestras de la literatura moderna, y su influencia se extiende a múltiples campos del pensamiento y el arte. La riqueza de sus personajes, la belleza de su prosa, y la profundidad de su visión hacen de esta novela una lectura esencial para cualquier amante de la literatura.");
            libro8.setCategoria("Literatura Francesa");
            libroRepositorio.save(libro8);

// Ejemplo 9
            Libro libro9 = new Libro();
            libro9.setTitulo("El Principito");
            libro9.setTipoMaterial("Libro");
            libro9.setAutor("Antoine de Saint-Exupéry");
            libro9.setEditorial("Éditions Gallimard");
            libro9.setAno_public("1943");
            libro9.setGenero("Fábula");
            libro9.setIsbn("9780156012195");
            libro9.setCant_dispon(12);
            libro9.setEstado_libro("Disponible");
            libro9.setResumen("El Principito es una fábula filosófica que narra la historia de un joven príncipe que viaja de planeta en planeta, aprendiendo valiosas lecciones sobre la vida, el amor y la soledad. A través de sus encuentros con diferentes personajes, como el rey, el hombre de negocios, y el farolero, el Principito descubre la naturaleza absurda y vacía de la vida adulta. La historia es contada desde la perspectiva de un aviador, que se encuentra varado en el desierto del Sahara y establece una amistad con el Principito. La novela es conocida por su simplicidad y su profundidad, ofreciendo una reflexión sobre la importancia de ver con el corazón en lugar de con los ojos, y de valorar lo que realmente importa en la vida. El Principito ha sido traducido a cientos de idiomas y es uno de los libros más vendidos de todos los tiempos. Su mensaje universal y su estilo poético han tocado los corazones de lectores de todas las edades, convirtiéndolo en una obra atemporal y querida.");
            libro9.setCategoria("Literatura Infantil");
            libroRepositorio.save(libro9);

// Ejemplo 10
            Libro libro10 = new Libro();
            libro10.setTitulo("La Odisea");
            libro10.setTipoMaterial("Libro");
            libro10.setAutor("Homero");
            libro10.setEditorial("Grecia Antigua");
            libro10.setAno_public("1000");
            libro10.setGenero("Épica");
            libro10.setIsbn("9780140268867");
            libro10.setCant_dispon(7);
            libro10.setEstado_libro("Disponible");
            libro10.setResumen("La Odisea es una de las obras épicas más antiguas y famosas de la literatura mundial. La historia narra las aventuras de Odiseo (Ulises en la mitología romana), rey de Ítaca, durante su viaje de regreso a casa después de la guerra de Troya. A lo largo de su viaje, Odiseo enfrenta numerosos desafíos, incluyendo encuentros con monstruos como Polifemo, el cíclope, y las Sirenas, así como con dioses y diosas que intentan ayudarlo o frustrarlo. A través de su astucia y valentía, Odiseo finalmente logra regresar a Ítaca, donde debe enfrentarse a los pretendientes que han invadido su hogar y reclamar su lugar como rey. La Odisea es tanto una historia de aventuras como una reflexión sobre la resistencia, la lealtad y el poder del ingenio humano. Homero utiliza un lenguaje poético y una estructura narrativa compleja para explorar temas como la identidad, el destino y la moralidad. La influencia de La Odisea en la literatura y la cultura occidental es inmensa, y sigue siendo una obra fundamental en el estudio de la literatura clásica.");
            libro10.setCategoria("Literatura Clásica");
            libroRepositorio.save(libro10);

// Ejemplo 11
            Libro libro11 = new Libro();
            libro11.setTitulo("La Divina Comedia");
            libro11.setTipoMaterial("Libro");
            libro11.setAutor("Dante Alighieri");
            libro11.setEditorial("Alighieri, Florencia");
            libro11.setAno_public("1308");
            libro11.setGenero("Poesía");
            libro11.setIsbn("9780140448955");
            libro11.setCant_dispon(5);
            libro11.setEstado_libro("Disponible");
            libro11.setResumen("La Divina Comedia es un poema épico que narra el viaje del autor a través del Infierno, el Purgatorio y el Paraíso. La obra, escrita en italiano, es una alegoría de la redención espiritual y una meditación sobre el pecado, la virtud y la justicia divina. Dante es guiado por el poeta romano Virgilio a través del Infierno y el Purgatorio, y por Beatriz, su amor idealizado, a través del Paraíso. A lo largo del camino, Dante encuentra a almas condenadas y benditas, y reflexiona sobre la naturaleza de la vida después de la muerte y la justicia divina. La Divina Comedia es conocida por su estructura precisa, su simbolismo complejo y su profundo conocimiento de la filosofía, la teología y la literatura de la época. A través de su viaje, Dante ofrece una visión del universo medieval y una reflexión sobre la condición humana. La obra ha tenido una influencia duradera en la literatura y la cultura occidental, y sigue siendo una de las grandes obras maestras de la literatura mundial.");
            libro11.setCategoria("Literatura Italiana");
            libroRepositorio.save(libro11);

// Ejemplo 12
            Libro libro12 = new Libro();
            libro12.setTitulo("Donde los árboles cantan");
            libro12.setTipoMaterial("Libro");
            libro12.setAutor("Laura Gallego García");
            libro12.setEditorial("SM");
            libro12.setAno_public("2011");
            libro12.setGenero("Fantasía");
            libro12.setIsbn("9788467550030");
            libro12.setCant_dispon(9);
            libro12.setEstado_libro("Disponible");
            libro12.setResumen("Donde los árboles cantan es una novela de fantasía que sigue la historia de Viana, una joven noble que ve su vida cambiar drásticamente cuando su reino es invadido por un ejército enemigo. Decidida a luchar por su libertad y la de su pueblo, Viana se embarca en un viaje hacia el misterioso Gran Bosque, donde se dice que los árboles cantan. A lo largo de su aventura, Viana descubre secretos antiguos y se enfrenta a peligros sobrenaturales, mientras busca su lugar en un mundo que está cambiando rápidamente. La novela combina elementos de la mitología y el folclore con una narrativa épica y emotiva, explorando temas como la valentía, el amor y el sacrificio. Laura Gallego García crea un mundo rico y detallado, poblado por personajes complejos y situaciones llenas de suspense. Donde los árboles cantan ha sido aclamada por su capacidad para capturar la imaginación de los lectores y por su mensaje de esperanza y determinación.");
            libro12.setCategoria("Juvenil");
            libroRepositorio.save(libro12);

// Ejemplo 13
            Libro libro13 = new Libro();
            libro13.setTitulo("La Sombra del Viento");
            libro13.setTipoMaterial("Libro");
            libro13.setAutor("Carlos Ruiz Zafón");
            libro13.setEditorial("Planeta");
            libro13.setAno_public("2001");
            libro13.setGenero("Ficción");
            libro13.setIsbn("9788408163384");
            libro13.setCant_dispon(7);
            libro13.setEstado_libro("Disponible");
            libro13.setResumen("La Sombra del Viento es una novela que mezcla el misterio, el romance y la historia, situada en la Barcelona de la posguerra. La historia comienza cuando el joven Daniel Sempere descubre un libro olvidado en el Cementerio de los Libros Olvidados, una biblioteca secreta donde se almacenan libros olvidados por el tiempo. Fascinado por la historia del autor, Julián Carax, Daniel se embarca en una búsqueda para descubrir la verdad detrás de la vida y muerte de Carax, y en el proceso, desentierra secretos oscuros que han sido enterrados durante años. La novela es una reflexión sobre el poder de los libros, el amor y la obsesión. Carlos Ruiz Zafón utiliza un estilo lírico y una narrativa intrincada para crear una atmósfera de misterio y suspense, donde cada personaje tiene un papel crucial en el desenlace de la historia. La Sombra del Viento ha sido aclamada internacionalmente por su capacidad para capturar la imaginación de los lectores y por su evocadora representación de la Barcelona de la época.");
            libro13.setCategoria("Ficción Contemporánea");
            libroRepositorio.save(libro13);

// Ejemplo 14
            Libro libro14 = new Libro();
            libro14.setTitulo("El amor en los tiempos del cólera");
            libro14.setTipoMaterial("Libro");
            libro14.setAutor("Gabriel García Márquez");
            libro14.setEditorial("Editorial Oveja Negra");
            libro14.setAno_public("1985");
            libro14.setGenero("Realismo mágico");
            libro14.setIsbn("9780307389732");
            libro14.setCant_dispon(6);
            libro14.setEstado_libro("Disponible");
            libro14.setResumen("El amor en los tiempos del cólera es una novela que explora el poder del amor y la pasión a lo largo de varias décadas. La historia sigue a Florentino Ariza y Fermina Daza, quienes se enamoran en su juventud pero son separados por circunstancias y decisiones de la vida. Florentino dedica su vida a amar a Fermina, incluso mientras ella se casa con otro hombre y construye una familia. A lo largo de los años, Florentino espera pacientemente la oportunidad de reconquistar a Fermina, mientras lleva una vida de aventuras amorosas y logros personales. García Márquez explora temas como el amor no correspondido, el envejecimiento, y la perseverancia. La novela está escrita en un estilo lírico y poético, característico del realismo mágico, y ofrece una visión profunda y conmovedora de la condición humana. A través de sus personajes complejos y su trama intrincada, El amor en los tiempos del cólera se ha convertido en una de las obras más queridas y apreciadas de la literatura latinoamericana.");
            libro14.setCategoria("Literatura Hispanoamericana");
            libroRepositorio.save(libro14);

// Ejemplo 15
            Libro libro15 = new Libro();
            libro15.setTitulo("Frankenstein");
            libro15.setTipoMaterial("Libro");
            libro15.setAutor("Mary Shelley");
            libro15.setEditorial("Lackington, Hughes, Harding, Mavor & Jones");
            libro15.setAno_public("1818");
            libro15.setGenero("Gótico");
            libro15.setIsbn("9780486282114");
            libro15.setCant_dispon(3);
            libro15.setEstado_libro("Disponible");
            libro15.setResumen("Frankenstein es una novela gótica que narra la trágica historia del Dr. Victor Frankenstein, un joven científico que, obsesionado con los secretos de la vida, crea una criatura a partir de partes de cadáveres. Sin embargo, horrorizado por su propia creación, Frankenstein abandona a la criatura, que pronto se convierte en un ser resentido y solitario. La criatura, rechazado por la sociedad debido a su apariencia monstruosa, busca venganza contra su creador, llevando a una serie de tragedias que culminan en una confrontación final en el Ártico. La novela explora temas como la ambición desmedida, la responsabilidad moral y las consecuencias del avance científico. Shelley utiliza un estilo gótico y elementos del romanticismo para crear una historia que es tanto una advertencia contra la arrogancia humana como una reflexión sobre la soledad y el rechazo. Frankenstein ha sido aclamada como una de las primeras obras de ciencia ficción y sigue siendo una de las novelas más influyentes de la literatura inglesa.");
            libro15.setCategoria("Literatura Gótica");
            libroRepositorio.save(libro15);

        }
    }
}

