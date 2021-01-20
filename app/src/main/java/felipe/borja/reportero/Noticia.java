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

    public Noticia(String cabeza, String cuerpo, String link,String medioTxt) {
        this.cabeza = cabeza;
        this.cuerpo = cuerpo;
        this.medio = stringToMedio(medioTxt);
        this.link = link;
    }

    private medios stringToMedio(String s){
        if( s.contains("omercio") )return medios.elcomercio;
        if( s.contains("lahora") )return medios.lahora;
        if( s.contains("ercurio") )return medios.mercurio;
        if( s.contains("rimicias") )return medios.primicias;
        if( s.contains("epublica") )return medios.larepublica;
        if( s.contains("elegrafo") )return medios.eltelegrafo;
        if( s.contains("oticias") )return medios.ultimasnoticias;
        return medios.eluniverso;
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
