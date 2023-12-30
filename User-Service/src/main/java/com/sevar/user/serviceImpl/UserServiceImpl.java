package com.sevar.user.serviceImpl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.sevar.user.model.User;
import com.sevar.user.repository.UserRepository;
import com.sevar.user.service.UserService;
import com.sevar.user.service.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService 
{
		@Autowired
		private UserRepository repo;
				
		@Autowired
		private PasswordEncoder encoder;
		
		@Override
		public User create(User user) 
		{
			user.setPassword(encoder.encode(user.getPassword()));
			return repo.save(user);
		}
		
		@Override
		public List<User> getAll() 
		{
			List<User> list = repo.findAll();
			return list;
		}
		
		@Override
		public User getUser(Integer id) 
		{
			Optional<User> opt = repo.findById(id);
			if(opt.isPresent())
			{
				User users = opt.get();
				return users;
			}
			else
				throw new UserNotFoundException("User Not Found");
		}
}
