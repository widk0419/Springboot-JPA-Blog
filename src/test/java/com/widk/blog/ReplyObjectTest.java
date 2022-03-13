package com.widk.blog;

import org.junit.Test;

import com.widk.blog.model.Reply;

public class ReplyObjectTest {

	@Test
	public void 투스트링테스트() {
		Reply reply = Reply.builder().id(1).user(null).board(null).content("안녕").build();

		System.out.println(reply); // 오브젝트를 출력하면 자동으로 toString()이 호출됨!!
	}

}
