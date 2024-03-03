package com.laluna.laluna.controller;


import com.laluna.laluna.service.ReplyService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class ReplyControllerTest {

    @Mock
    ReplyService replyService;

    @InjectMocks
    ReplyController replyController;
}
