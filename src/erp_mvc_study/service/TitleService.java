package erp_mvc_study.service;

import java.util.List;

import erp_mvc_study.dao.TitleDao;
import erp_mvc_study.dao.impl.TitleDaoImpl;
import erp_mvc_study.dto.Title;

public class TitleService {
    private TitleDao dao = TitleDaoImpl.getInstance();


    public List<Title> getTitleList() {
        return dao.selectTitleByAll();
    }

    public int getNextTitleNo() {
        return dao.getNextNo();
    }
    
    public int addTitle(Title title) {
        return dao.insertTitle(title);
    }
    
    public Title getTitle(Title title) {
        return dao.selectTitleByNo(title);
    }
    
    public int delTitle(Title title) {
        return dao.deleteTitle(title);
    }
    
    public int modifyTitle(Title title) {
        return dao.updateTitle(title);
    }
}
