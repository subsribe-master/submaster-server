### UserController
+ 사용자를 저장한다.
+ 사용자를 저장시 이메일은 필수이다.
+ 사용자를 저장할 시 패드워드는 필수이다.
+ 사용자를 저장할시 로그인타입은 필수이다.

### LoginController
+ 일반 로그인을 한다.
+ 일반 로그인을 할 시 이메일은 필수이다.
+ 일반 로그인을 할 시 비밀번호는 필수이다

### UserReadService
+ 사용자를 저장한 후 사용자를 조회한다.
+ 사용자를 저장한 후 이메일로 사용자를 조회한다.

### LoginService
+ 사용자가 존재하면 조회한다.
+ 사용자가 존재하지 않으면 예외가 발생한다.
+ 비밀번호가 일치하지 않으면 예외가 발생한다.

### BankController
+ 은행을 저장한다.
+ 은행을 저장할시 은행명은 필수이다.
+ 은행을 조회한다.

### AccountController
+ 계좌 정보를 저장한다.
+ 계좌 정보저장시 은행정보는 필수입니다.
+ 계좌 정보저장시 사용자정보는 필수입니다.

### BankService
+ 은행을 저장한다.
+ 은행을 조회한다.
+ 은행을 조회할시 일치하는 은행이 없으면 빈배열이 내려온다.

### AccountService
+ 계좌 정보를 저장한다.
+ 사용자의 계좌정보들을 가져온다.
+ 계좌 정보를 가져온다.

### SubscriptionController
+ 구독서비스를 저장한다.
+ 구독서비스를 저장할시 서비스명은 필수이다.
+ 구독서비스를 ID 조회한다.
+ 구독서비스를 이름으로 조회한다.
+
### SubscriptionPriceController
+ 구독서비스ID로 가격목록을 조회한다.

### SubscriptionService
+ 구독서비스를 저장한다.
+ 구독서비스를 ID 조회한다.
+ 구독서비스를 ID 조회시 존재하지 않으면 예외가 발생한다.
+ 구독서비스를 이름으로 조회한다.
+ 구독서비스를 이름으로 조회시 존재하지 않으면 예외가 발생한다.

### SubscriptionPriceService
+ 구독서비스를 가격을 저장한다.
+ 구독서비스를 가격을 저장시 구독서비스가 존재하지 않으면 예외가 발생한다.
+ 구독서비스의 가격을 조회한다.
+ 구독서비스의 가격을 조회할시 존재하지 않으면 빈배열을 반환한다.
