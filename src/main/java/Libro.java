import java.util.Objects;

/**
 * Representa un libro dentro del catálogo de la biblioteca universitaria.
 *
 * <p>Administra la información bibliográfica básica (título y autor) y controla el estado de
 * disponibilidad para operaciones de préstamo, devolución y comparación por contenido.
 */
public class Libro {

  /** Título oficial del libro (inmutable tras su creación). */
  private final String titulo;

  /** Nombre del autor del libro (inmutable tras su creación). */
  private final String autor;

  /** Estado actual de disponibilidad; todo libro inicia disponible para préstamo. */
  private boolean disponible = true;

  /**
   * Crea una nueva instancia de {@code Libro} en estado inicialmente disponible.
   *
   * @param titulo título del libro
   * @param autor nombre del autor del libro
   */
  public Libro(String titulo, String autor) {
    this.titulo = titulo;
    this.autor = autor;
  }

  /**
   * Obtiene el título del libro.
   *
   * @return título registrado del libro
   */
  public String getTitulo() {
    return titulo;
  }

  /**
   * Obtiene el autor del libro.
   *
   * @return autor registrado del libro
   */
  public String getAutor() {
    return autor;
  }

  /**
   * Consulta si el libro se encuentra disponible para ser prestado.
   *
   * @return {@code true} si el libro está disponible; {@code false} si ya está prestado
   */
  public boolean estaDisponible() {
    return disponible;
  }

  /**
   * Registra el préstamo del libro cambiando su estado a no disponible.
   *
   * <p>Si el libro ya fue prestado previamente, se interrumpe la operación lanzando una excepción
   * de estado ilegal para proteger la consistencia del inventario.
   *
   * @throws IllegalStateException si el libro no se encuentra disponible
   */
  public void prestar() {
    // Valida la precondición antes de modificar el estado del libro.
    if (!disponible) {
      throw new IllegalStateException("Libro no disponible");
    }

    disponible = false;
  }

  /**
   * Registra la devolución del libro y restablece su estado como disponible.
   */
  public void devolver() {
    // Marca el ejemplar como disponible nuevamente para futuros préstamos.
    disponible = true;
  }

  /**
   * Compara el título de este libro con otro título evaluando su contenido textual.
   *
   * <p>Utiliza {@link Objects#equals(Object, Object)} en lugar del operador {@code ==} para
   * comparar valores y evitar errores de referencia (CWE-595) o {@code NullPointerException}.
   *
   * @param otroTitulo título con el cual se desea comparar
   * @return {@code true} si ambos títulos tienen el mismo contenido; {@code false} si difieren
   */
  public boolean esMismoTitulo(String otroTitulo) {
    // Comparación segura por contenido (tolera valores null sin lanzar excepciones).
    return Objects.equals(titulo, otroTitulo);
  }
}