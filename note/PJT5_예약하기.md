# PJT5

product_price 테이블에 price_type_name 값은 아래와 같습니다.

성인(A), 청소년(Y), 유아(B), 셋트(S), 장애인(D), 지역주민(C), 어얼리버드(E) 기타 다른 유형이 있다면 위와 겹치지 않게 1자로 정의하여 기입, VIP(V), R석(R), B석(B), S석(S), 평일(D)



atomic design



jdbc usingColumns(): mapper에서 사용할 칼럼만 선택



optional chaining

@ResponseStatus(HttpStatus.OK)

삼항연산자

노드 찾아갈 때 명시적으로 -> closest?



## 알게 된 것

### FormData

form 태그 필드의 값을 key-value 쌍으로 치환 가능한 데이터 타입

```typescript
/** Provides a way to easily construct a set of key/value pairs representing form fields and their values, which can then be easily sent using the XMLHttpRequest.send() method. It uses the same format a form would use if the encoding type were set to "multipart/form-data". */
interface FormData {
    append(name: string, value: string | Blob, fileName?: string): void;
    delete(name: string): void;
    get(name: string): FormDataEntryValue | null;
    getAll(name: string): FormDataEntryValue[];
    has(name: string): boolean;
    set(name: string, value: string | Blob, fileName?: string): void;
    forEach(callbackfn: (value: FormDataEntryValue, key: string, parent: FormData) => void, thisArg?: any): void;
}

declare var FormData: {
    prototype: FormData;
    new(form?: HTMLFormElement): FormData;
};

interface FormData {
    [Symbol.iterator](): IterableIterator<[string, FormDataEntryValue]>;
    /**
     * Returns an array of key, value pairs for every entry in the list.
     */
    entries(): IterableIterator<[string, FormDataEntryValue]>;
    /**
     * Returns a list of keys in the list.
     */
    keys(): IterableIterator<string>;
    /**
     * Returns a list of values in the list.
     */
    values(): IterableIterator<FormDataEntryValue>;
}

// FormData의 값 확인
for (var pair of formData.entries()) { console.log(pair[0]+ ', ' + pair[1]); }
```



### Optional Chaining

**옵셔널 체이닝을 남용하지 마세요.**

`?.`는 존재하지 않아도 괜찮은 대상에만 사용해야 합니다.

사용자 주소를 다루는 위 예시에서 논리상 `user`는 반드시 있어야 하는데 `address`는 필수값이 아닙니다. 그러니 `user.address?.street`를 사용하는 것이 바람직합니다.

실수로 인해 `user`에 값을 할당하지 않았다면 바로 알아낼 수 있도록 해야 합니다. 그렇지 않으면 에러를 조기에 발견하지 못하고 디버깅이 어려워집니다.

### JavaScript 페이지 이동 관련 Methods

1. location.href = "주소";
2. history.back() : 이전 페이지로 이동
3. location.reload() : 현재 페이지 refresh
4. history.forward() : 페이지 앞으로 가기











## Refactoring

1. Closest로 비교적 명시적인 탐색
2. Optional Chaining을 통한 속성 탐색