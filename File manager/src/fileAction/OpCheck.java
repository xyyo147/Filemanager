package file;

import java.util.ArrayList;
import java.util.List;

public class OpCheck {              //记录已打开文件表的list
    private final int MAX_OPFILE_NUMBER=5;
    private List<OpenTable>openTables=new ArrayList<OpenTable>();
    private int op_length=0;          //已打开文件登记表中登记的文件数量
    OpCheck(){}
    public void update_check(){}        //更新已打开登记表的信息
    public List getOpenTables(){          //得到已打开文件表的list
        return this.openTables;
    }
    public boolean setOpenTable(OpenTable a){           //添加已打开文件表结点
        if(op_length<MAX_OPFILE_NUMBER){
            openTables.add(a);
            op_length++;
            return true;
        }
        return false;
    }

}
