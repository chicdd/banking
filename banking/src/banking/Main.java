package banking;

import java.util.Scanner;

public class Main {
	private static Account[] accountArray = new Account[100];
	private static Scanner scan = new Scanner(System.in); //전역에 생성. 밑에 만들면 함수 못만드니까
	

	public static void main(String[] args) {
		boolean run = true;
		while(run) {
			System.out.println("============================================");
			System.out.println("1,계좌 생성 | 2.계좌 목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("============================================");
			System.out.print("선택>");
			
			int selNum = scan.nextInt();
			if(selNum == 1) {
				createAccount();
			}else if(selNum == 2) {
				accountList();
			}else if(selNum == 3) {
				deposit();
			}else if(selNum == 4) {
				withdraw();
			}else if(selNum == 5) {
				run = false;
			}else {
				System.out.println("지원하지 않는 기능입니다.");
				
			} 
			
		}
		System.out.println("프로그램 종료");
	}

	//출금
	private static void withdraw() {
		System.out.println("=========");
		System.out.println("3.출금");
		System.out.println("=========");
		
		System.out.println("계좌 번호 : ");
		String ano = scan.next();
		
		
		
		Account account = findAccount(ano); //계좌검색
		if(account == null) {
			System.out.println("계좌가 없습니다.");
		}else {
			while(true) {
				System.out.println("출금액 : ");
				int money = scan.nextInt();
			
			if(money > account.getBalance() || money < 0) {
				System.out.println("잔액이 부족합니다. 다시 입력해 주세요.");
			}else {
				account.setBalance(account.getBalance() - money);
				System.out.println("결과 : 출금을 성공하였습니다.");
				break;
			}
			
		}
		
		
		}
		
	}

	//예금
	private static void deposit() {
		System.out.println("=========");
		System.out.println("3.예금");
		System.out.println("=========");
		
		
			
		
		
		System.out.println("계좌 번호 : ");
		String ano = scan.next();
		
		System.out.println("입금액 : ");
		int money = scan.nextInt();
		
		Account account = findAccount(ano);
		
		if(account == null) {
			System.out.println("계좌가 없습니다.");
		}else {
			account.setBalance(account.getBalance() + money);
			System.out.println("결과 : 입금을 성공하였습니다.");
		}
	}
	
	

	//계좌 목록
	private static void accountList() {
		System.out.println("=========");
		System.out.println("2.계좌 목록");
		System.out.println("=========");
		
		//저장된 모든 계좌가 출력
		for(int i = 0; i < accountArray.length; i++) {
			Account account = accountArray[i];
			if(account != null) { //주의! null이 아닌 경우 출력
				System.out.print("계좌 번호 : " + account.getAno() + "    "); //private이므로 get으로 접근
				System.out.print("계좌주 : " + account.getOwner() + "    ");
				System.out.println("잔고 : " + account.getBalance() + "    ");
			}
		}
	}

	//계좌 생성
	private static void createAccount() {
		System.out.println("=========");
		System.out.println("1.계좌 생성");
		System.out.println("=========");
		
		System.out.println("계좌 번호 : ");
		String ano = scan.next();
		
		System.out.println("계좌주 : ");
		String owner = scan.next();
		
		System.out.println("초기 입금액 : ");
		int balance = scan.nextInt() ;
		
		
		Account newAccount = new Account(ano, owner, balance);
		for(int i = 0; i < accountArray.length; i++) {  //배열 100개 중 0번으로 들어감
			if(accountArray[i] ==null) { //주의! null인 곳에 저장
				accountArray[i] = newAccount;
				System.out.println("결과 : 계좌가 생성되었습니다.");
				break;
			}
		}
	}
	//계좌 검색
		private static Account findAccount(String ano) {
			Account account = null;
			for(int i = 0; i < accountArray.length; i++) {
				if(accountArray[i] != null) { //코드를 반드시 명시
					String dbAno = accountArray[i].getAno();
					if(dbAno.equals(ano)) { //이미 저장된 계좌번호와 외부에서 찾는 계좌번호가 일치하면
						account = accountArray[i]; //계좌를 가져옴
						break;
					}
				}
			}
			return account;
			
		}
}
