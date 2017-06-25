package sample.dao;

import java.util.List;
import sample.entity.Tokens;

/**
 * Created by ggladko97 on 25.06.17.
 */
public interface TokensDao {
  void insert(Tokens user);
  void updateTokens(Tokens user);
  List<Tokens> listTokens();
  Tokens getTokensById(int id);
  void removeTokens(int id);
}
