package quangtung.aprotrain.com.modal;

public class Remider {
    private int id;
    private String noidung;
    private boolean quantrong;

    public Remider() {
    }

    public Remider(String noidung, boolean quantrong) {
        this.noidung = noidung;
        this.quantrong = quantrong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public boolean isQuantrong() {
        return quantrong;
    }

    public void setQuantrong(boolean quantrong) {
        this.quantrong = quantrong;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
