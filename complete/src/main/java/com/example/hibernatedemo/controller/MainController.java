package com.example.hibernatedemo.controller;

import com.example.hibernatedemo.dto.UserDTO;
import com.example.hibernatedemo.entity.Comment;
import com.example.hibernatedemo.entity.User;
import com.example.hibernatedemo.repository.UserRepository;
import com.example.hibernatedemo.service.CommentService;
import com.example.hibernatedemo.service.CommonService;
import com.example.hibernatedemo.service.UserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value="demo")
public class MainController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private CommonService commonService;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private List<HttpMessageConverter> list;

	@PostMapping(value = "read")
	public @ResponseBody
	UserDTO test(@RequestBody UserDTO userDTO) {
		return userDTO;
	}

	@GetMapping(value = "sample")
	public @ResponseBody UserDTO test(@RequestParam String str, @RequestParam boolean failFast) {
		ObjectMapper map = new ObjectMapper();
		if( failFast) {
			map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		} else {
			map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		}
		UserDTO userDTO = null;
		try {
			userDTO = map.readValue(str, UserDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return userDTO;
	}

	@PostMapping(value = "post")
	public @ResponseBody UserDTO post(@RequestBody UserDTO userDTO) {
		return userDTO;
	}

	@PostMapping(value="add")
	public @ResponseBody String addNewUser (@RequestParam String name
			, @RequestParam String email, @RequestParam Boolean test) {
		User n = new User();
		n.setName(name);
		n.setEmail(email);
		n.setTest(test);
		userRepository.save(n);
		return "Saved";
	}

	@GetMapping(value="all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

//	@GetMapping(value = "id/{id}")
//	public @ResponseBody User getByPrimary(@PathVariable(name = "id") Integer id) {
//		return userRepository.findById(id).get();
//	}

	@GetMapping(value = "name/{name}")
	public @ResponseBody User getByName(@PathVariable(name = "name") String name) {
//		userRepository.findByName(name);
//		Comment comment = commentService.getCommentById(1);
//		return userService.getUserByName(name);
		User user =  commonService.get(name);
		Session session = (Session) entityManager.getDelegate();
		List<Comment> l = user.getComments();
//		session.detach(user);
		commonService.process(user);
		return user;
	}

	@GetMapping(value= "test")
	public String create(@RequestParam(name = "name") String name,
						 @RequestParam(name = "comment") String comment,
						 @RequestParam(name="flag") Boolean flag) {
		commonService.commonMethod(name, comment, flag);
		return "success";
	}

	@GetMapping(value= "dept")
	public String getDepartment(@RequestParam(name = "id") Integer id,
								@RequestParam(name="name") String name) {
		commonService.getDepartment(id, name);
		return "success";
	}

	@RequestMapping(value = "/videos/upload", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadVideo(MultipartFile files,
							  String id) {
		return "";
	}

	@RequestMapping(value = "/tnt")
	public String uploadVideoTest() {
		return "hello world!!!";
	}
}
