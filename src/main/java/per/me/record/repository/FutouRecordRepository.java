package per.me.record.repository;

import org.springframework.data.repository.CrudRepository;
import per.me.record.entity.FutouRecord;

public interface FutouRecordRepository extends CrudRepository<FutouRecord, Integer> {
}
