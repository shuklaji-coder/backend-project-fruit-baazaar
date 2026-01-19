package in.rohanshukla.fruitsapi.fruitrepo;

import in.rohanshukla.fruitsapi.entity.FruitEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface fruitrepository2 extends MongoRepository<FruitEntity,String> {
}
