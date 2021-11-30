/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auction.util;

import java.util.Timer;
import java.util.TimerTask;
import auction.data.TimerDAO;
import java.util.Date;
import auction.data.ItemTO;
import java.util.ArrayList;

/**
 *
 * @author aris
 */
public class TimerBean {

    Timer timer;
    TimerDAO tdao;

    public void init() {
        timer = new Timer();
        tdao = new TimerDAO();
    }

    public void loadTasks() {
        try {
            ArrayList itemsList = tdao.getItemList();
            if (itemsList != null) {
                java.util.Iterator i = itemsList.iterator();
                while (i.hasNext()) {
                    ItemTO item = (ItemTO) i.next();
                    schedule(item.getStopDate().longValue(), item.getId());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTask(Integer itemId) {
        try {
            ItemTO item = tdao.loadItem(itemId);
            schedule(item.getStopDate().longValue(), item.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void schedule(long d, Integer itemId) {
        RemindTask rt = new RemindTask();
        rt.setId(itemId);
        timer.schedule(rt, new Date(d));
    }

    class RemindTask extends TimerTask {

        Integer id;

        public void setId(Integer i) {
            this.id = i;
        }

        public Integer getId() {
            return this.id;
        }

        public void run() {
            try {
                tdao.buyItem(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            timer.cancel(); //Terminate the timer thread

        }
    }
}
