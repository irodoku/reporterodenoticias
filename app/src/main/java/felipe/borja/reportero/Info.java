package felipe.borja.reportero;

public class Info {
    private String titulo;
    private String link;

    public Info(String titulo, String link) {
        this.titulo = titulo;
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
