package Model_class;

import Model_enum.*;

public class Transaksi {
    private String nama, _NIK;
	private Usia usia;
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String get_NIK() {
        return _NIK;
    }
    public void set_NIK(String _NIK) {
        this._NIK = _NIK;
    }
    public Usia getUsia() {
        return usia;
    }
    public void setUsia(Usia usia) {
        this.usia = usia;
    }
}
