package service;

import domain.Architect;
import domain.Designer;
import domain.Employee;
import domain.Programmer;

import java.time.temporal.Temporal;

public class TeamService {
    private static int counter =1;
    private final int MAX_MEMBER =5;//限制开发团队人数
    private Programmer[] team =new Programmer[MAX_MEMBER];
    private  int total;//记录开发团队中实际人数

    public Programmer[] getTeam(){
        Programmer[] team =new Programmer[total];
        for(int i =0;i<team.length;i++){
            team[i] =this.team[i];
        }
        return  team;
    }

    public void getMember(Employee e) throws  TeamException{
        //成员已满 无法添加
        if(total >= MAX_MEMBER){
            throw new TeamException("成员满");
        }
        //该成员不是开发人员,无法添加
        if(!(e instanceof  Programmer)){
            throw new TeamException("无法添加");
        }
        //该成员已在开发团队中
        if(isExist(e)){
            throw new TeamException("无法添加");
        }
        //已经是团队成员
        //在休假,无法添加
        Programmer p = (Programmer) e;//不会出现类型转换异常
        if(p.getStatus().getNAME().equals("BUST")){
            throw new TeamException("无法添加");
        }else  if("VOCATION".equals(p.getStatus().getNAME())){
            throw new TeamException("无法添加");
        }

        //团队中只能右一名架构师
        //获取team中已有的成员中架构师,设计师,程序员的人数
        int numOfArch =0,numofDes=0,nuOfPro=0;
        for(int i =0;i<total;i++){
            if(team[i] instanceof Architect){
                numOfArch++;
            }else if(team[i] instanceof Designer){
                numofDes++;
            }else if(team[i] instanceof Programmer){
                nuOfPro++;
            }
        }

        if(p instanceof Architect){
            if(numOfArch>=1){
                throw new TeamException("无法添加");
            }else if(p instanceof Designer){
                if(numofDes >=2){
                    throw new TeamException("无法添加");
                }
            }else if(p instanceof Programmer){
                if(nuOfPro >=3){
                    throw new TeamException("无法添加");
                }
            }
            //将p添加到现有的team中
            team[total++] =p;
            p.setStatus(Status.BUSY);
            p.setMemberId(counter++);
        }
    }

    private boolean isExist(Employee e) {
        for(int i =0;i<total;i++){
            if(team[i].getId() ==e.getId()){
                return true;
            }
        }return  false;
    }

    public void removerMember(int memberId) throws TeamException{
        int i =0;
        for(i =0;i<total;i++){
            if(team[i].getMemberId() ==memberId){
                team[i].setStatus(Status.FREE);
                break;
            }
        }
        if(i==total){
            throw new TeamException("删除失败");
        }

        for(int j=i+1;j<total;j++){
            team[j-1]=team[j];
        }

        team[--total] =null;
    }
}
