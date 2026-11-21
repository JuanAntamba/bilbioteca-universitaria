import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Suite de pruebas unitarias para la clase {@link Libro}.
 *
 * <p>Verifica el ciclo de vida de un libro (creación, préstamo, devolución y comparación de
 * títulos), siguiendo el patrón AAA (Arrange, Act, Assert) para garantizar cobertura completa.
 */
class LibroTest {

  /**
   * Verifica que un libro recién instanciado almacene sus datos y comience disponible.
   */
  @Test
  void deberiaCrearLibroDisponible() {
    // Arrange & Act: se crea un libro con título y autor válidos.
    Libro libro = new Libro("1984", "George Orwell");

    // Assert: se validan los atributos iniciales y la disponibilidad por defecto.
    assertEquals("1984", libro.getTitulo());
    assertEquals("George Orwell", libro.getAutor());
    assertTrue(libro.estaDisponible());
  }

  /**
   * Verifica que prestar un libro disponible cambie su estado a no disponible.
   */
  @Test
  void deberiaPrestarLibroDisponible() {
    // Arrange: se instancia un libro inicialmente disponible.
    Libro libro = new Libro("1984", "George Orwell");

    // Act: se ejecuta el préstamo del libro.
    libro.prestar();

    // Assert: el libro deja de estar disponible.
    assertFalse(libro.estaDisponible());
  }

  /**
   * Verifica que intentar prestar un libro ya prestado lance {@link IllegalStateException}.
   */
  @Test
  void deberiaLanzarErrorAlPrestarLibroNoDisponible() {
    // Arrange: se crea el libro y se presta una primera vez.
    Libro libro = new Libro("1984", "George Orwell");
    libro.prestar();

    // Act & Assert: un segundo préstamo consecutivo debe lanzar IllegalStateException.
    assertThrows(IllegalStateException.class, libro::prestar);
  }

  /**
   * Verifica que devolver un libro previamente prestado lo vuelva a dejar disponible.
   */
  @Test
  void deberiaDevolverLibroPrestado() {
    // Arrange: se crea y presta el libro.
    Libro libro = new Libro("1984", "George Orwell");
    libro.prestar();

    // Act: se registra la devolución del ejemplar.
    libro.devolver();

    // Assert: el libro vuelve a estar disponible.
    assertTrue(libro.estaDisponible());
  }

  /**
   * Verifica que {@link Libro#esMismoTitulo(String)} compare el texto por contenido y no por
   * referencia en memoria.
   */
  @Test
  void deberiaCompararTitulosPorContenido() {
    // Arrange: se fuerza una instancia nueva en memoria con el mismo contenido textual.
    Libro libro = new Libro("1984", "George Orwell");
    String otroTitulo = new String("1984");

    // Act & Assert: la comparación por contenido debe retornar true pese a ser objetos distintos.
    assertTrue(libro.esMismoTitulo(otroTitulo));
  }

  /**
   * Verifica que la comparación de títulos retorne {@code false} cuando los textos no coinciden.
   */
  @Test
  void deberiaRetornarFalsoCuandoTitulosSonDiferentes() {
    // Arrange: se crea un libro con el título base.
    Libro libro = new Libro("1984", "George Orwell");

    // Act & Assert: un título diferente no debe coincidir.
    assertFalse(libro.esMismoTitulo("Rebelión en la granja"));
  }

  /**
   * Verifica que la comparación sea segura ante valores nulos y retorne {@code false}.
   */
  @Test
  void deberiaRetornarFalsoCuandoOtroTituloEsNulo() {
    // Arrange: se crea un libro con un título no nulo.
    Libro libro = new Libro("1984", "George Orwell");

    // Act & Assert: pasar null debe devolver false sin lanzar NullPointerException.
    assertFalse(libro.esMismoTitulo(null));
  }
}