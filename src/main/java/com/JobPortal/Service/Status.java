package com.JobPortal.Service;

import org.springframework.stereotype.Component;

import io.lettuce.core.dynamic.annotation.CommandNaming;

public enum Status {

	NOT_SELECTED,SELECTED,IN_PROCESS,NONE,VIEWD
}
