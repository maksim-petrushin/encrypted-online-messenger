package com.encryptedmessenger.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.encryptedmessenger.dto.FriendshipDto;
import com.encryptedmessenger.enteties.Friendship;
import com.encryptedmessenger.enteties.impl.DefaultFriendship;

public class FriendshipDtoToFriendshipConverter {
	private static FriendshipDtoToFriendshipConverter instance = null;
	private UserDtoToUserConverter userConverter;
	
	public static FriendshipDtoToFriendshipConverter getInstance() {
		if(instance == null) {
			instance = new FriendshipDtoToFriendshipConverter();
		}
		return instance;
	}
	
	public FriendshipDto convertFriendshipIdToFriendshipDtoWithOnlyId(int friendshipId) {
		FriendshipDto friendshipDto = new FriendshipDto();
		friendshipDto.setId(friendshipId);
		return friendshipDto;
	}
	
	public Friendship convertFriendshipDtoToFriendship(FriendshipDto friendshipDto) {
		if(friendshipDto == null) {
			return null;
		}
		
		Friendship friendship = new DefaultFriendship();
		friendship.setFirstFriend(userConverter.getInstance().convertUserDtoToUser(friendshipDto.getFirstFriend()));
		friendship.setSecondFriend(userConverter.getInstance().convertUserDtoToUser(friendshipDto.getSecondFriend()));
		friendship.setFriendshipId(friendshipDto.getId());
		
		return friendship;
	}
	
	public FriendshipDto convertFriendshipToFriendshipDto(Friendship friendship) {
		FriendshipDto friendshipDto = new FriendshipDto();
		
		friendshipDto.setFirstFriend(userConverter.getInstance().convertUserToUserDto(friendship.getFirstFriend()));
		friendshipDto.setSecondFriend(userConverter.getInstance().convertUserToUserDto(friendship.getSecondFriend()));
		friendshipDto.setId(friendship.getFriendshipId());
		
		return friendshipDto;
	}
	
	public List<Friendship> convertFriendshipDtosToFriendships(List<FriendshipDto> friendshipDtos){
		List<Friendship> friendships = new ArrayList<>();
		
		for(FriendshipDto friendshipDto : friendshipDtos) {
			friendships.add(convertFriendshipDtoToFriendship(friendshipDto));
		}
		return friendships;
	}
}
