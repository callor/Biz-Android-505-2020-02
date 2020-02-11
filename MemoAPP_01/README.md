## RecyclerView를 사용한 List 표현

* RecyclerView만으로는 List를 표현하기가 다소불편하다.
* RecyclerView의 요소를 표현할 Apdater를 생성하고
* Adapter 내부에 Holder를 생성하여 Inflater를 수행하여 모양을 만든다.

1. Holder에 사용할 item view 페이지를 생성