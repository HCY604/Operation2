package service;

import java.util.List;
import model.Goods;

public interface GoodsService {
    void save(Goods goods);
    List<Goods> findAll();
}
