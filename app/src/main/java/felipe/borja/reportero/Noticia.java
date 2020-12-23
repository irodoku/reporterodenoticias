package felipe.borja.reportero;

public class Noticia {
    private String cabeza;
    private String cuerpo;
    private String link;
    private medios medio;

    public Noticia() {
    }

    public Noticia(String cabeza, String cuerpo, String link,medios medio) {
        this.cabeza = cabeza;
        this.cuerpo = cuerpo;
        this.medio = medio;
        this.link = link;
    }

    public String getCabeza() {
        return cabeza;
    }

    public void setCabeza(String cabeza) {
        this.cabeza = cabeza;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public medios getMedio() {
        return medio;
    }

    public void setMedio(medios medio) {
        this.medio = medio;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
