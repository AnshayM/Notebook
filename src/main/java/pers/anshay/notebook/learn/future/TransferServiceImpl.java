package pers.anshay.notebook.learn.future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author machao
 * @date 2022/6/23
 */
@Service
public class TransferServiceImpl implements ITransferService {
	@Autowired
	private IAccountService accountService;


	@Override
	public CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount) {
		return accountService.add(fromAccount, -1 * toAccount)
				.thenCompose(v -> accountService.add(toAccount, amount));
	}
}

interface ITransferService {
	CompletableFuture<Void> transfer(int fromAccount, int toAccount, int amount);
}

interface IAccountService {
	CompletableFuture<Void> add(int account, int amount);
}

class Client{
	@Autowired
	private ITransferService transformer;
	public void syncInvoke() throws ExecutionException, InterruptedException {
		transformer.transfer(1000, -100,100).get();
		System.out.println("转账完成");

	}
	void asyncInvoke() {
		transformer.transfer(1000, -100,100).thenRun(()->System.out.println("转账完成"));

	}
}
