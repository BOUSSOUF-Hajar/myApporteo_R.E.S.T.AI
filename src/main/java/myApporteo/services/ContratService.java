package myApporteo.services;

import java.io.IOException;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import myApporteo.entities.Contrat;
import myApporteo.repositories.ContratRepository;

import org.springframework.util.StringUtils;
@Service
public class ContratService {
	@Autowired
	  private ContratRepository contratRepository;

	  public Contrat store(MultipartFile file) throws IOException {
	    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	    Contrat FileDB = new Contrat(fileName, file.getContentType(), file.getBytes());

	    return contratRepository.save(FileDB);
	  }

	  public Contrat getFile(String id) {
	    return contratRepository.findById(id).get();
	  }
	  
	  public Stream<Contrat> getAllFiles() {
	    return contratRepository.findAll().stream();
	  }
}
