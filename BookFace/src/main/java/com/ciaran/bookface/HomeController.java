package com.ciaran.bookface;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.ciaran.bookface.entity.Friend;
import com.ciaran.bookface.entity.FriendRequest;
import com.ciaran.bookface.entity.Post;
import com.ciaran.bookface.entity.User;
import com.ciaran.bookface.service.FriendRequestService;
import com.ciaran.bookface.service.FriendService;
import com.ciaran.bookface.service.PostService;
import com.ciaran.bookface.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userSession")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private PostService postService;
	private UserService userService;
	private FriendRequestService friendRequestService;
	private FriendService friendService;
	
	@Autowired(required=true)
	@Qualifier(value="userService")
	public void setUserService(UserService us){
		this.userService = us;
	}
	
	@Autowired(required=true)
	@Qualifier(value="postService")
	public void setPostService(PostService ps){
		this.postService = ps;
	}
	
	@Autowired(required=true)
	@Qualifier(value="friendRequestService")
	public void setFriendRequestService(FriendRequestService fs){
		this.friendRequestService = fs;
	}
	
	@Autowired(required=true)
	@Qualifier(value="friendService")
	public void setFriendRequestService(FriendService fs){
		this.friendService = fs;
	}
	//login first page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		model.addAttribute("user",new User());
		return "login";
	}
		
	//User Logging in
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User u,Model model){
		User user=userService.login(u.getName(), u.getPassword());
		if(user != null){
			model.addAttribute("userSession",user.getName());
			return "redirect:/home";
		}
		model.addAttribute("user",new User());
		model.addAttribute("errorLogin","Wrong username or password was entered");
		return "login";
	}
	
	//User Sign up
	@RequestMapping(value = "signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("user", new User());
		return "sign_up";
	}
	//signing up
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User u,Model model) {
		User userCheck=userService.getUserByName(u.getName());
		if(userCheck != null){
			model.addAttribute("user", new User());
			model.addAttribute("userError","This user take already exits");
			return "sign_up";
		}
		if(!(u.getName().equals("")) && u.getPassword().length()>6){
			this.userService.addUser(u);
			return "redirect:/";
		}
		model.addAttribute("user", new User());
		if(u.getName().equals(""))
			model.addAttribute("nameError", "The user name you entered is empty");
		if(u.getPassword().length()<6){
			model.addAttribute("passwordError", "The pasword you entered needs to be greater than 6 charactors long");
		}
		return "sign_up";
	}	
	
	//The home page to bookFace
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @ModelAttribute("userSession") String currentUser){
		model.addAttribute("post", new Post());
		List<Post> allPosts=postService.getPosts();
		List<Friend> friendList=friendService.getAllFriends(currentUser);
		List<Post> wall=new ArrayList<Post>();
		for(Post p: allPosts){
			if(currentUser.equals(p.getName())){
					p.setName("You");
					wall.add(p);
			}
			else{
				for (Friend f: friendList){
					if(f.getFriendName().equals(p.getName())){
						wall.add(p);
					}
				}
			}
		}
		model.addAttribute("friends", friendList);
		model.addAttribute("wall", wall);
		return "home";
	}
	
	//Creating a new post
	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String addPost (@ModelAttribute("post") Post p,@ModelAttribute("userSession") String currentUser,Model model) {
		model.addAttribute("post", new Post());
		if(p==null){
			return "home";
		}
		if(p.getPost().equals(""))
			return "home";
		p.setName(currentUser);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		//Date currentTime=LocalDateTime.now();
		p.setPosttime(timeStamp);
		
		this.postService.addPost(p);
		model.addAttribute("test", p.getPost());
		model.addAttribute("user", currentUser);
		return "redirect:/home";
	}
	//loging out
	@RequestMapping(value = "/logout")
	public String logout(Model model){
		model.addAttribute("userSession", "");
		return "redirect:/";
	}
	
	//User Search
	@RequestMapping(value = "friendSearch/{usersearch}" ,method = RequestMethod.GET)
	public String userSearch(Model model, @PathVariable("usersearch") String searchName,
			@ModelAttribute("userSession")String userName){
		List <User> userList=this.userService.userSearch(searchName);
		User currentUser=null;
		//remove the current user from the search
		if(userList.size()==0)
			model.addAttribute("noResults","No results were found with that search");
		for(User u:userList){
			if(u.getName().equals(userName)){
				currentUser=u;
			}
		}
		if(currentUser!= null){
			userList.remove(currentUser);
		}
		
		//Removes Users
		List<Friend> friendList=friendService.getAllFriends(userName);
		List<User> removeList=new ArrayList();
		userList.remove(currentUser);
		//get the indexs that you want to remove
		for(User u:userList){
			for(Friend f:friendList){
				if(f.getFriendName().equals(u.getName()))
					removeList.add(u);
			}
		}
		for(int j=0; j<removeList.size(); j++){
			userList.remove(removeList.get(j));
		}
		
		model.addAttribute("searchName",searchName);
		model.addAttribute("userList", userList);
		return "friendSearch";
	}
	//Send Friend Request
	@RequestMapping(value = "sendRequest/{receiverName}" ,method = RequestMethod.GET)
	public String sendRequest(Model model, @PathVariable("receiverName") String receiverName,
			@ModelAttribute("userSession") String userName,@ModelAttribute("searchName")String searchName){
		FriendRequest fr=new FriendRequest();
		fr.setReceiverName(receiverName);
		fr.setRequesterName(userName);
		friendRequestService.sendRequest(fr);
		//return "redirect:/friendSearch/"+searchName;
		return "redirect:/";
	}
	//get all the friend requests
	@RequestMapping(value = "friendRequests" ,method = RequestMethod.GET)
	public String getFriendRequest(Model model,@ModelAttribute("userSession") String userName){
		List<FriendRequest> friendRequestList=friendRequestService.getAllFriendRequest(userName);
		if(friendRequestList.size()==0){
			model.addAttribute("noResults", "Hahaha you have no friend requests!");

		}
		model.addAttribute("friendRequestList", friendRequestList);
		return "friend_request";
	}
	//Accept Friend Request
	@RequestMapping(value = "acceptFriend/{friendName}" ,method = RequestMethod.GET)
	public String acceptFriend(Model model,@PathVariable("friendName") String friendName,
			@ModelAttribute("userSession") String userName){
		Friend friend=new Friend();
		friend.setUserName(userName);
		friend.setFriendName(friendName);
		friendService.acceptFriend(friend);
		
		friendRequestService.removeRequest(userName,friendName);
		
		friend.setUserName(friendName);
		friend.setFriendName(userName);
		friendService.acceptFriend(friend);
		
		model.addAttribute("addedFriend", friendName+" has been added has your friend");
		
		return "friend_request";
	}
	
		//The Friend page to bookFace
		@RequestMapping(value = "/FriendPage/{friendName}", method = RequestMethod.GET)
		public String friendPage(Locale locale, Model model, @ModelAttribute("userSession") String currentUser,
				@PathVariable("friendName") String friendName){
			model.addAttribute("post", new Post());
			List<Post> allPosts=postService.getPosts();
			List<Friend> friendList=friendService.getAllFriends(friendName);
			List<Post> wall=new ArrayList<Post>();
			for(Post p: allPosts){
				if(friendName.equals(p.getName())){
						p.setName("You");
						wall.add(p);
				}
				else{
					for (Friend f: friendList){
						if(f.getFriendName().equals(p.getName())){
							wall.add(p);
						}
					}
				}
			}
			model.addAttribute("friendPage", "This is "+friendName+" page");
			model.addAttribute("wall", wall);
			return "home";
		}
}
