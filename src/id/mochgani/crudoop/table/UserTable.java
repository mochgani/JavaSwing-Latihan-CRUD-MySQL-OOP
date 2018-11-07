package id.mochgani.crudoop.table;

import id.mochgani.crudoop.entity.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class UserTable extends AbstractTableModel{
    
    private List<User> list = new ArrayList<>();
    
    public UserTable(){
        
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex){
            case 0 : return list.get(rowIndex).getId();
            case 1 : return list.get(rowIndex).getNama();
            case 2 : return list.get(rowIndex).getUsername();
            case 3 : return list.get(rowIndex).getAkses();
            default:return null;
        }
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0 : return "id";
            case 1 : return "nama";
            case 2 : return "username";
            case 3 : return "akses";
            default:return null;
        }
    }
    
    public void setData(List<User> list){
        this.list= list;
        fireTableDataChanged();
    }
    
    public void insertUser(User usr){
        this.list.add(usr);
        fireTableDataChanged();
    }
    
    public void updateUser(int index, User usr){
        this.list.set(index, usr);
        fireTableDataChanged();
    }
    
    public void deleteUser(int index){
        this.list.remove(index);
        fireTableDataChanged();
    }
    
    public User getUser(int index){
        return list.get(index);
    }
    
    public void clear(){
        list.clear();
    }
    
}
