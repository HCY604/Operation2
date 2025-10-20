package service.impl;

import java.util.List;

import dao.GoodsDao;
import dao.impl.GoodsDaoImpl;
import model.Goods;
import service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public void save(Goods goods) {
        goodsDao.insert(goods);
    }

    @Override
    public List<Goods> findAll() {
        return goodsDao.findAll();
    }
}
