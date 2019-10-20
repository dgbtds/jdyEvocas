package cn.ihep.jdy.release.service.login;

import cn.ihep.jdy.release.dao.model.ResultModel;

public interface GenericService {
    ResultModel login(String mobil,String pass);

    ResultModel getWeek(Long userId);
}
