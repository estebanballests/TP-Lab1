public class Medal {
    private String pais;
    private int oro;
    private int plata;
    private int bronce;

    public Medal(String pais, int oro, int plata, int bronce) {
        this.pais = pais;
        this.oro = oro;
        this.plata = plata;
        this.bronce = bronce;
    }

    public String getPais() {
        return pais;
    }

    public int getOro() {
        return oro;
    }

    public int getPlata() {
        return plata;
    }

    public int getBronce() {
        return bronce;
    }
}
