package com.example.demo.Auth;

import java.io.IOException;

import org.glassfish.grizzly.filterchain.Filter;
import org.glassfish.grizzly.filterchain.FilterChain;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.FilterChainEvent;
import org.glassfish.grizzly.filterchain.NextAction;

public class ClientFilter implements Filter {

	@Override
	public void onAdded(FilterChain filterChain) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onRemoved(FilterChain filterChain) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFilterChainChanged(FilterChain filterChain) {
		// TODO Auto-generated method stub

	}

	@Override
	public NextAction handleRead(FilterChainContext ctx) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NextAction handleWrite(FilterChainContext ctx) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NextAction handleConnect(FilterChainContext ctx) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NextAction handleAccept(FilterChainContext ctx) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NextAction handleEvent(FilterChainContext ctx, FilterChainEvent event) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exceptionOccurred(FilterChainContext ctx, Throwable error) {
		// TODO Auto-generated method stub

	}

}
