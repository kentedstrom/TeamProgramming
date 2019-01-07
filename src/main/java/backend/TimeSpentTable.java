package backend;

import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class TimeSpentTable {

    private String taskName;
    private ArrayList<String> memberTimes;

    // counter for memberNumber
    public static int timeTableMemberNumber = -1;

    public TimeSpentTable(){}

    public TimeSpentTable(String taskName, ArrayList<String> memberTimes){
        this.memberTimes = memberTimes;
        this.taskName = taskName;
    }

    public String getTaskName(){
        return this.taskName;
    }

    // return the membertime that needs to be returned
    public ArrayList<String> getMemberTimes(){
        return memberTimes;
    }
        /*
        timeTableMemberNumber += 1;
        System.out.println(timeTableMemberNumber);
        if(timeTableMemberNumber>=memberTimes.size()){
            return null;
        }else{
            ObservableValue<String> timeResult = new ReadOnlyStringWrapper(memberTimes.get(timeTableMemberNumber));
            return timeResult;
        }
    }
    */

}
