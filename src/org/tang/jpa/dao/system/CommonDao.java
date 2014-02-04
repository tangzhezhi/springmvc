package org.tang.jpa.dao.system;

import org.springframework.stereotype.Repository;
import org.tang.jpa.utils.Page;

@Repository
public interface CommonDao {

	public Page selectDictory(Page page) ;

}
