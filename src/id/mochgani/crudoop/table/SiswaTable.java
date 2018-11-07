package id.mochgani.crudoop.table;

import id.mochgani.crudoop.entity.Siswa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class SiswaTable extends AbstractTableModel{
    
    private List<Siswa> list = new ArrayList<>();
    
    public SiswaTable(){
        
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getNis();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getJenis();
            case 3 : return list.get(rowIndex).getKelas();
            case 4 : return list.get(rowIndex).getAlamat();
            default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0 : return "nis";
            case 1 : return "nama";
            case 2 : return "kelamin";
            case 3 : return "kelas";
            case 4 : return "alamat";
            default:return null;
        }
    }
    
    public void setData(List<Siswa> list){
        this.list= list;
        fireTableDataChanged();
    }
    
    public void insertSiswa(Siswa sis){
        this.list.add(sis);
        fireTableDataChanged();
    }
    
    public void updateSiswa(int index, Siswa sis){
        this.list.set(index, sis);
        fireTableDataChanged();
    }
    
    public void deleteSiswa(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public Siswa getSiswa(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
}
