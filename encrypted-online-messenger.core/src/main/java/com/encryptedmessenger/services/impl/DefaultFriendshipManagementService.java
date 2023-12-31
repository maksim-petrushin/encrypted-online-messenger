package com.encryptedmessenger.services.impl;

import java.util.Arrays;

import com.encryptedmessenger.enteties.Friendship;
import com.encryptedmessenger.enteties.User;
import com.encryptedmessenger.enteties.impl.DefaultFriendship;
import com.encryptedmessenger.services.FriendshipManagementService;

public class DefaultFriendshipManagementService implements FriendshipManagementService{
		
		private static final String NO_SUCH_USER_ERROR_MESSAGE = "There is no such user";
		private static final String FRIENDSHIP_EXISTS_ERROR_MESSAGE = "This friendship already exists";
		private static final String NO_ERROR_MESSAGE = "";
		
		private static final int DEFAULT_FRIENDSHIP_CAPACITY = 10;
		
		private static DefaultFriendshipManagementService instance;
		
		private Friendship[] friendships;
		private int lastFriendshipIndex;
		
		{
			friendships = new Friendship[DEFAULT_FRIENDSHIP_CAPACITY];
		}

		private DefaultFriendshipManagementService() {
		}
		
		@Override
		public String createFriendship(User firstUser, User secondUser) {
			if (firstUser == null || secondUser == null) {
				return NO_SUCH_USER_ERROR_MESSAGE;
			}
			
			if(!doesFriendshipExist(firstUser, secondUser)) {
				Friendship friendship = new DefaultFriendship(firstUser, secondUser);
				
				if (friendships.length <= lastFriendshipIndex) {
					friendships = Arrays.copyOf(friendships, friendships.length << 1);
				}
				
				friendships[lastFriendshipIndex++] = friendship;
				return NO_ERROR_MESSAGE;
			}
			return FRIENDSHIP_EXISTS_ERROR_MESSAGE;
			
		}
		@Override
		public boolean doesFriendshipExist(User firstUser, User secondUser) {
			for (Friendship friendship : friendships) {
				if(friendship != null) {
					
				if ((friendship.getFirstFriend() == firstUser && friendship.getSecondFriend() == secondUser)
				|| (friendship.getFirstFriend() == secondUser && friendship.getSecondFriend() == firstUser)) {
					return true;
				}
				}
			}
			return false;
		}

		public static FriendshipManagementService getInstance() {
			if (instance == null) {
				instance = new DefaultFriendshipManagementService();
			}
			return instance;
		}

		
		
		@Override
		public User[] getFriendsByUser(User user) {
			int nonNullFriendshipsAmount = 0;
			for (Friendship friendship : friendships) {
				if (friendship != null && (friendship.getFirstFriend() == user || friendship.getSecondFriend() == user)) {
					nonNullFriendshipsAmount++;
				}
			}
			
			User[] nonNullUsers = new User[nonNullFriendshipsAmount];
			
			int index = 0;
			for (Friendship friendship : friendships) {
				if (friendship != null && (friendship.getFirstFriend() == user || friendship.getSecondFriend() == user)) {
					if(friendship.getFirstFriend() == user) {
						nonNullUsers[index++] = friendship.getSecondFriend();
					}
					else {
						nonNullUsers[index++] = friendship.getFirstFriend();
					}
				}
			}
			return nonNullUsers;		
		}
		
		void clearServiceState() {
			lastFriendshipIndex = 0;
			friendships = new Friendship[DEFAULT_FRIENDSHIP_CAPACITY];
		}
		
		
//		// Stream API version of the method
//		@Override
//		public User[] getUsers() {
//			return Arrays.stream(users)
//					.filter(Objects::nonNull)
//					.toArray(User[]::new);
//		}
}
