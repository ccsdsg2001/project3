package service;

import domain.*;
import org.junit.Test;

import static service.Data.*;

public class NameListService {
    private Employee[] employees;

    public NameListService() {//給employees及數組原數初始化
        employees = new Employee[EMPLOYEES.length];

        for (int i = 0; i < EMPLOYEES.length; i++) {
            int type = Integer.parseInt(EMPLOYEES[i][0]);
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;
            switch (type) {
                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] =new Programmer(id,name,age,salary,equipment);
                    break;
                case DESIGNER:
                    equipment =createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] =new Designer(id,name,age,salary,equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock =Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] =new Architect(id,name,age,salary,equipment,bonus,stock);
                    break;

            }

        }
    }

        public Employee[] getALLEmployees() {
            return employees;
        }

        public Employee getEmployee(int id) throws TeamException{
            for(int i =0;i<employees.length;i++){
                if(employees[i].getId() == id ){
                    return employees[i];
                }
            }

            throw new TeamException("找不到");
        }

        @Test
        public void testGetEmployee(){
        NameListService service =new NameListService();
        int id;
        try {
            Employee employee =service.getEmployee(32);
            System.out.println(employee);
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }
        }


        private Equipment createEquipment ( int index){
            int type = Integer.parseInt(EQUIPMENTS[index][0]);
            String model =EQUIPMENTS[index][1];
            switch (type){
                case PC:
                    return new PC(model,EQUIPMENTS[index][2]);
                case NOTEBOOK:
                    double price =Double.parseDouble(EQUIPMENTS[index][2]);
                    return new NoteBook(model,price);
                case PRINTER:
                    return new Printer(model,EQUIPMENTS[index][2]);

        }
        return null;
    }




}
