package dao;

import java.util.List;
import model.Goods;

public interface GoodsDao {
    void insert(Goods goods);
    List<Goods> findAll();
}
