package v1.dewu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import v1.dewu.entity.CodeUser;
import v1.dewu.entity.Identification;
import v1.dewu.repository.IdentificationRepository;
import v1.dewu.repository.UserRepository;

@Service
public class IdentificationService {
    @Autowired
    IdentificationRepository identificationRepository;

    public Identification getIdenification(long serialNumber)
    {
        return identificationRepository.findById(serialNumber).get();
    }

    public boolean exsist (long serialNumber){
        return identificationRepository.existsById(serialNumber);
    }
    public void saveOrUpdate(Identification i)
    {
        identificationRepository.save(i);
    }

    public void deleteIdentification(long serialNumber){identificationRepository.deleteById(serialNumber);}



}
