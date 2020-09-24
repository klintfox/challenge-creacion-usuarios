package com.klinux.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.klinux.dto.UsuarioDto;
import com.klinux.entity.Phone;
import com.klinux.entity.User;
import com.klinux.repository.LoginRepository;
import com.klinux.repository.PhoneRepository;
import com.klinux.util.GeneralMessages;

@DataJpaTest
class LoginServiceImplTest {

	private UsuarioDto usuarioDto = new UsuarioDto();

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	private User user;
	private String emailInsert = "roman@gmail.cl";
	private String emailFind = "klint@hotmail.cl";

	@Test
	public void testEmailValidator() {
		String email = "klint@gmail.cl";
		boolean flag = usuarioDto.validateEmailPattern(email);
		assertThat(flag);
	}

	@Test
	public void testPasswordValidator() {
		String password = "Program26";
		boolean flag = usuarioDto.validatePasswordPattern(password);
		assertTrue(flag);
	}

	@Test
	void testFindUserbyEmail() {
		User userDb = loginRepository.findByEmail(emailFind);
		assertThat(userDb.getEmail().equals(emailFind));
	}

	@Test
	public void testSaveUser() {
		String token = usuarioDto.generateToken(emailInsert);
		user = new User();
		user.setName("Klint");
		user.setEmail("roman@gmail.cl");
		user.setPassword("Pruebas22");
		user.setCreated(new Date());
		user.setModified(new Date());
		user.setLastLogin(new Date());
		user.setIsactive(GeneralMessages.IS_ACTIVE);
		user.setToken(token);
		entityManager.persist(user);
		User userDb = loginRepository.findByEmail(user.getEmail());
		if (userDb != null)
			assertThat(userDb.getEmail().equals(emailInsert));
	}

	@Test
	public void testSavePhones() {
		String phoneNumber = "315987654321";
		String token = usuarioDto.generateToken(emailInsert);
		user = new User();
		user.setName("Klint");
		user.setEmail("roman@gmail.cl");
		user.setPassword("Pruebas22");
		user.setCreated(new Date());
		user.setModified(new Date());
		user.setLastLogin(new Date());
		user.setIsactive(GeneralMessages.IS_ACTIVE);
		user.setToken(token);
		User userDb = entityManager.persist(user);
		Phone phone = new Phone();
		phone.setPhoneNumber(phoneNumber);
		phone.setCountrycode(57);
		phone.setCitycode(11);
		phone.setUser(userDb);
		entityManager.persist(phone);
		Phone phoneDb = phoneRepository.findByPhoneNumber(phoneNumber);
		if (phoneDb != null)
			assertThat(phoneDb.getPhoneNumber().equals(phoneNumber));
	}

}