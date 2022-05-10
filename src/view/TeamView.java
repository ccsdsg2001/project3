package view;

import domain.Employee;
import domain.Programmer;
import service.NameListService;
import service.TeamException;
import service.TeamService;

public class TeamView {
    private NameListService listService =new NameListService();
    private TeamService teamService=new TeamService();

    public void enterMainMenu(){
        char menu=0;
        boolean loopflag =true;
        while (loopflag) {

            if(menu!='1'){
                listAllEmployees();
            }
            System.out.println("1,2,3,4 请选择");
            menu = TSUtility.readMenuSelection();
            switch (menu) {
                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("是否要退出(y/n)");
                    char isExit =TSUtility.readConfirmSelection();
                    if(isExit=='Y') {
                        loopflag = false;
                    }
                    break;
            }
        }
    }

    public void listAllEmployees(){
        System.out.println("显示所有");
        listService.getALLEmployees();
        Employee[] employees =listService.getALLEmployees();
        if(employees==null || employees.length==0){
            System.out.println("没有");
        }else{
            System.out.println("id/xingming/");

            for(int i =0;i<employees.length;i++){
                System.out.println(employees[i]);
            }
        }

    }

    private void getTeam(){
        System.out.println("-----------------------");
        Programmer[] team = teamService.getTeam();
        if(team ==null || team.length ==0){
            System.out.println("开发团队没有成员");
        }else {
            System.out.println("if");
            for(int i =0;i<team.length;i++){
                System.out.println(team[i]);
            }
        }
        System.out.println("查看团队");

    }
    private void addMember(){
        System.out.println("添加");
        int id =TSUtility.readInt();
        try {


            Employee employee = listService.getEmployee(id);
        }catch (TeamException e){
            e.printStackTrace();
        }
        TSUtility.readReturn();
    }

    private void deleteMember(){
        System.out.println("删除");
        int id =TSUtility.readInt();
        char isDelete =TSUtility.readConfirmSelection();
        if(isDelete =='N'){
            return;
        }
        try {
            teamService.removerMember(id);
        }catch (TeamException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        TeamView view =new TeamView();
        view.enterMainMenu();
    }
}
