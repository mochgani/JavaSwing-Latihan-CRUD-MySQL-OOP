package id.mochgani.crudoop.service;

import id.mochgani.crudoop.entity.Siswa;
import java.util.List;

public interface ServiceSiswa {
    void insertSiswa(Siswa sis);
    void updateSiswa(Siswa sis);
    void deleteSiswa(Siswa sis);
    Siswa getByid(String id);
    List<Siswa> getsisSiswa();
}
