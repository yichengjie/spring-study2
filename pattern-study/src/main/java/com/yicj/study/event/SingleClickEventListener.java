package com.yicj.study.event;

public class SingleClickEventListener implements EventListener {
    @Override
    public void processEvent(Event event) {
        if("singleclick".equals(event.getType())){
            System.out.println("单击被触发了");
        }
    }
}
