#include <stdio.h>
#include <stdlib.h>

typedef struct _llNode{
	int w;
	int p;
	struct _llNode * n;
} LLNode;

int length, timeD, timeH, nApples, slowdown;

int timeC(LLNode * apple){
	return slowdown * apple->w * (length-apple->p);
}

int take(int weight, int time, LLNode * apples){
	int check1, check2;
	if (time >= timeH || apples == 0){
		return weight;
	}
	if (time + timeC(apples) > timeH){
		check1 = 0;
	} else {
		check2 = take(apples->w + weight, time + timeC(apples), apples->n);
	}
	return (check1 > check2 ? check1 : check2);
}
	
int main(){
	LLNode * head = malloc(sizeof(LLNode));
	LLNode * cur = head;
	scanf("%d %d %d %d %d\n", &length, &timeD, &timeH, &nApples, &slowdown);
	for(int i=0;i < nApples; i++){
		scanf("%d %d\n",&(cur->w),&(cur->p));
		cur->n = malloc(sizeof(LLNode));
	}
	free(cur->n);
	cur->n=0;
	printf("%d\n", take(0,timeD, head));
	return 0;
}
	