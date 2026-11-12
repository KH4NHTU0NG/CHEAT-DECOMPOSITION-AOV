/Applications/Map Có Menu.app/Wrapper/nhayduhacanh.app/Frameworks/QH.framework/QH:
(__TEXT,__text) section
+[QHGate load]:
000000000000628c	sub	sp, sp, #0x40
0000000000006290	stp	x29, x30, [sp, #0x30]
0000000000006294	add	x29, sp, #0x30
0000000000006298	stur	x0, [x29, #-0x8]
000000000000629c	stur	x1, [x29, #-0x10]
00000000000062a0	mov	x8, #0x3333333333333333
00000000000062a4	movk	x8, #0x3fd3, lsl #48
00000000000062a8	str	x8, [sp, #0x18]
00000000000062ac	ldr	d0, [sp, #0x18]
00000000000062b0	fmov	d1, #3.00000000
00000000000062b4	fcmp	d0, d1
00000000000062b8	cset	w8, ls
00000000000062bc	tbnz	w8, #0x0, 0x62c4
00000000000062c0	b	0x633c
00000000000062c4	ldr	d0, [sp, #0x18]
00000000000062c8	adrp	x8, 1 ; 0x7000
00000000000062cc	ldr	d1, [x8, #0xa78]
00000000000062d0	fmul	d0, d0, d1
00000000000062d4	fcvtzs	x1, d0
00000000000062d8	mov	x8, #0x0
00000000000062dc	mov	x0, x8
00000000000062e0	bl	0x78dc ; symbol stub for: _dispatch_time
00000000000062e4	adrp	x8, 2 ; 0x8000
00000000000062e8	ldr	x8, [x8, #0x18] ; literal pool symbol address: __dispatch_main_q
00000000000062ec	str	x0, [sp, #0x10]
00000000000062f0	mov	x0, x8
00000000000062f4	bl	0x793c ; symbol stub for: _objc_retainAutoreleaseReturnValue
00000000000062f8	mov	x29, x29
00000000000062fc	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006300	ldr	x1, [sp, #0x10]
0000000000006304	str	x0, [sp, #0x8]
0000000000006308	mov	x0, x1
000000000000630c	ldr	x1, [sp, #0x8]
0000000000006310	adrp	x2, 2 ; 0x8000
0000000000006314	add	x2, x2, #0x68
0000000000006318	bl	0x78c4 ; symbol stub for: _dispatch_after
000000000000631c	ldr	x0, [sp, #0x8]
0000000000006320	bl	0x7924 ; symbol stub for: _objc_release
0000000000006324	ldr	d0, [sp, #0x18]
0000000000006328	adrp	x8, 1 ; 0x7000
000000000000632c	ldr	d1, [x8, #0xa70]
0000000000006330	fadd	d0, d0, d1
0000000000006334	str	d0, [sp, #0x18]
0000000000006338	b	0x62ac
000000000000633c	ldp	x29, x30, [sp, #0x30]
0000000000006340	add	sp, sp, #0x40
0000000000006344	ret
___14+[QHGate load]_block_invoke:
0000000000006348	sub	sp, sp, #0x20
000000000000634c	stp	x29, x30, [sp, #0x10]
0000000000006350	add	x29, sp, #0x10
0000000000006354	str	x0, [sp, #0x8]
0000000000006358	str	x0, [sp]
000000000000635c	adrp	x8, 6 ; 0xc000
0000000000006360	ldr	x0, [x8, #0x2e0] ; Objc class ref: QHGate
0000000000006364	adrp	x8, 6 ; 0xc000
0000000000006368	ldr	x1, [x8, #0x108] ; Objc selector ref: start
000000000000636c	bl	0x7918 ; Objc message: +[QHGate start]
0000000000006370	ldp	x29, x30, [sp, #0x10]
0000000000006374	add	sp, sp, #0x20
0000000000006378	ret
+[QHGate start]:
000000000000637c	sub	sp, sp, #0x20
0000000000006380	stp	x29, x30, [sp, #0x10]
0000000000006384	add	x29, sp, #0x10
0000000000006388	str	x0, [sp, #0x8]
000000000000638c	str	x1, [sp]
0000000000006390	adrp	x8, 6 ; 0xc000
0000000000006394	add	x8, x8, #0x3e0
0000000000006398	ldrb	w9, [x8]
000000000000639c	tbnz	w9, #0x0, 0x63a4
00000000000063a0	b	0x63a8
00000000000063a4	b	0x63c8
00000000000063a8	mov	w8, #0x1
00000000000063ac	adrp	x9, 6 ; 0xc000
00000000000063b0	add	x9, x9, #0x3e0
00000000000063b4	strb	w8, [x9]
00000000000063b8	ldr	x0, [sp, #0x8]
00000000000063bc	adrp	x9, 6 ; 0xc000
00000000000063c0	ldr	x1, [x9, #0x110] ; Objc selector ref: verify
00000000000063c4	bl	0x7918 ; Objc message: -[x0 verify]
00000000000063c8	ldp	x29, x30, [sp, #0x10]
00000000000063cc	add	sp, sp, #0x20
00000000000063d0	ret
+[QHGate currentKey]:
00000000000063d4	sub	sp, sp, #0x60
00000000000063d8	stp	x29, x30, [sp, #0x50]
00000000000063dc	add	x29, sp, #0x50
00000000000063e0	stur	x0, [x29, #-0x8]
00000000000063e4	stur	x1, [x29, #-0x10]
00000000000063e8	adrp	x8, 6 ; 0xc000
00000000000063ec	ldr	x0, [x8, #0x2e8] ; Objc class ref: _OBJC_CLASS_$_NSBundle
00000000000063f0	ldur	x2, [x29, #-0x8]
00000000000063f4	adrp	x8, 6 ; 0xc000
00000000000063f8	ldr	x1, [x8, #0x118] ; Objc selector ref: bundleForClass:
00000000000063fc	bl	0x7918 ; Objc message: +[NSBundle bundleForClass:]
0000000000006400	mov	x29, x29
0000000000006404	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006408	adrp	x8, 6 ; 0xc000
000000000000640c	ldr	x1, [x8, #0x120] ; Objc selector ref: infoDictionary
0000000000006410	stur	x0, [x29, #-0x20]
0000000000006414	bl	0x7918 ; Objc message: -[x0 infoDictionary]
0000000000006418	mov	x29, x29
000000000000641c	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006420	adrp	x8, 6 ; 0xc000
0000000000006424	ldr	x1, [x8, #0x128] ; Objc selector ref: objectForKey:
0000000000006428	str	x0, [sp, #0x28]
000000000000642c	adrp	x2, 2 ; 0x8000
0000000000006430	add	x2, x2, #0x108 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006434	bl	0x7918 ; Objc message: -[x0 objectForKey:]
0000000000006438	mov	x29, x29
000000000000643c	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006440	stur	x0, [x29, #-0x18]
0000000000006444	ldr	x0, [sp, #0x28]
0000000000006448	bl	0x7924 ; symbol stub for: _objc_release
000000000000644c	ldur	x0, [x29, #-0x20]
0000000000006450	bl	0x7924 ; symbol stub for: _objc_release
0000000000006454	ldur	x0, [x29, #-0x18]
0000000000006458	adrp	x8, 6 ; 0xc000
000000000000645c	ldr	x1, [x8, #0x130] ; Objc selector ref: length
0000000000006460	bl	0x7918 ; Objc message: -[x0 length]
0000000000006464	cbnz	x0, 0x64dc
0000000000006468	adrp	x8, 6 ; 0xc000
000000000000646c	ldr	x0, [x8, #0x2e8] ; Objc class ref: _OBJC_CLASS_$_NSBundle
0000000000006470	adrp	x8, 6 ; 0xc000
0000000000006474	ldr	x1, [x8, #0x138] ; Objc selector ref: mainBundle
0000000000006478	bl	0x7918 ; Objc message: +[NSBundle mainBundle]
000000000000647c	mov	x29, x29
0000000000006480	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006484	adrp	x8, 6 ; 0xc000
0000000000006488	ldr	x1, [x8, #0x120] ; Objc selector ref: infoDictionary
000000000000648c	str	x0, [sp, #0x20]
0000000000006490	bl	0x7918 ; Objc message: -[x0 infoDictionary]
0000000000006494	mov	x29, x29
0000000000006498	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000649c	adrp	x8, 6 ; 0xc000
00000000000064a0	ldr	x1, [x8, #0x128] ; Objc selector ref: objectForKey:
00000000000064a4	str	x0, [sp, #0x18]
00000000000064a8	adrp	x2, 2 ; 0x8000
00000000000064ac	add	x2, x2, #0x108 ; Objc cfstring ref: @"bad cfstring ref"
00000000000064b0	bl	0x7918 ; Objc message: -[x0 objectForKey:]
00000000000064b4	mov	x29, x29
00000000000064b8	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000064bc	ldur	x8, [x29, #-0x18]
00000000000064c0	stur	x0, [x29, #-0x18]
00000000000064c4	mov	x0, x8
00000000000064c8	bl	0x7924 ; symbol stub for: _objc_release
00000000000064cc	ldr	x0, [sp, #0x18]
00000000000064d0	bl	0x7924 ; symbol stub for: _objc_release
00000000000064d4	ldr	x0, [sp, #0x20]
00000000000064d8	bl	0x7924 ; symbol stub for: _objc_release
00000000000064dc	ldur	x0, [x29, #-0x18]
00000000000064e0	adrp	x8, 6 ; 0xc000
00000000000064e4	ldr	x1, [x8, #0x130] ; Objc selector ref: length
00000000000064e8	bl	0x7918 ; Objc message: -[x0 length]
00000000000064ec	cbnz	x0, 0x6500
00000000000064f0	sub	x0, x29, #0x18
00000000000064f4	adrp	x1, 2 ; 0x8000
00000000000064f8	add	x1, x1, #0x128 ; Objc cfstring ref: @"bad cfstring ref"
00000000000064fc	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006500	ldur	x8, [x29, #-0x18]
0000000000006504	str	x8, [sp, #0x10]
0000000000006508	cbz	x8, 0x6518
000000000000650c	ldr	x8, [sp, #0x10]
0000000000006510	str	x8, [sp, #0x8]
0000000000006514	b	0x6524
0000000000006518	adrp	x8, 2 ; 0x8000
000000000000651c	add	x8, x8, #0x128 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006520	str	x8, [sp, #0x8]
0000000000006524	ldr	x8, [sp, #0x8]
0000000000006528	mov	x0, x8
000000000000652c	bl	0x7930 ; symbol stub for: _objc_retain
0000000000006530	sub	x8, x29, #0x18
0000000000006534	str	x0, [sp]
0000000000006538	mov	x0, x8
000000000000653c	mov	x8, #0x0
0000000000006540	mov	x1, x8
0000000000006544	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006548	ldr	x0, [sp]
000000000000654c	ldp	x29, x30, [sp, #0x50]
0000000000006550	add	sp, sp, #0x60
0000000000006554	b	0x7900 ; symbol stub for: _objc_autoreleaseReturnValue
+[QHGate verify]:
0000000000006558	sub	sp, sp, #0xf0
000000000000655c	stp	x29, x30, [sp, #0xe0]
0000000000006560	add	x29, sp, #0xe0
0000000000006564	stur	x0, [x29, #-0x8]
0000000000006568	stur	x1, [x29, #-0x10]
000000000000656c	ldur	x0, [x29, #-0x8]
0000000000006570	adrp	x8, 6 ; 0xc000
0000000000006574	ldr	x1, [x8, #0x140] ; Objc selector ref: currentKey
0000000000006578	bl	0x7918 ; Objc message: -[x0 currentKey]
000000000000657c	mov	x29, x29
0000000000006580	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006584	stur	x0, [x29, #-0x18]
0000000000006588	ldur	x0, [x29, #-0x18]
000000000000658c	adrp	x8, 6 ; 0xc000
0000000000006590	ldr	x8, [x8, #0x2f0] ; Objc class ref: _OBJC_CLASS_$_NSCharacterSet
0000000000006594	adrp	x9, 6 ; 0xc000
0000000000006598	ldr	x1, [x9, #0x148] ; Objc selector ref: URLQueryAllowedCharacterSet
000000000000659c	str	x0, [sp, #0x68]
00000000000065a0	mov	x0, x8
00000000000065a4	bl	0x7918 ; Objc message: +[NSCharacterSet URLQueryAllowedCharacterSet]
00000000000065a8	mov	x29, x29
00000000000065ac	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000065b0	adrp	x8, 6 ; 0xc000
00000000000065b4	ldr	x1, [x8, #0x150] ; Objc selector ref: stringByAddingPercentEncodingWithAllowedCharacters:
00000000000065b8	ldr	x8, [sp, #0x68]
00000000000065bc	str	x0, [sp, #0x60]
00000000000065c0	mov	x0, x8
00000000000065c4	ldr	x2, [sp, #0x60]
00000000000065c8	bl	0x7918 ; Objc message: -[x0 stringByAddingPercentEncodingWithAllowedCharacters:]
00000000000065cc	mov	x29, x29
00000000000065d0	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000065d4	str	x0, [sp, #0x58]
00000000000065d8	cbz	x0, 0x65e8
00000000000065dc	ldr	x8, [sp, #0x58]
00000000000065e0	str	x8, [sp, #0x50]
00000000000065e4	b	0x65f4
00000000000065e8	adrp	x8, 2 ; 0x8000
00000000000065ec	add	x8, x8, #0x128 ; Objc cfstring ref: @"bad cfstring ref"
00000000000065f0	str	x8, [sp, #0x50]
00000000000065f4	ldr	x8, [sp, #0x50]
00000000000065f8	mov	x0, x8
00000000000065fc	bl	0x7930 ; symbol stub for: _objc_retain
0000000000006600	stur	x0, [x29, #-0x20]
0000000000006604	ldr	x0, [sp, #0x58]
0000000000006608	bl	0x7924 ; symbol stub for: _objc_release
000000000000660c	ldr	x0, [sp, #0x60]
0000000000006610	bl	0x7924 ; symbol stub for: _objc_release
0000000000006614	adrp	x8, 6 ; 0xc000
0000000000006618	ldr	x0, [x8, #0x2f8] ; Objc class ref: _OBJC_CLASS_$_NSURL
000000000000661c	adrp	x8, 6 ; 0xc000
0000000000006620	ldr	x8, [x8, #0x300] ; Objc class ref: _OBJC_CLASS_$_NSString
0000000000006624	ldur	x9, [x29, #-0x20]
0000000000006628	adrp	x10, 6 ; 0xc000
000000000000662c	ldr	x1, [x10, #0x158] ; Objc selector ref: stringWithFormat:
0000000000006630	str	x0, [sp, #0x48]
0000000000006634	mov	x0, x8
0000000000006638	adrp	x2, 2 ; 0x8000
000000000000663c	add	x2, x2, #0x168 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006640	mov	x8, sp
0000000000006644	adrp	x10, 2 ; 0x8000
0000000000006648	add	x10, x10, #0xe8 ; Objc cfstring ref: @"bad cfstring ref"
000000000000664c	str	x10, [x8]
0000000000006650	str	x9, [x8, #0x8]
0000000000006654	bl	0x7918 ; Objc message: +[NSString stringWithFormat:]
0000000000006658	mov	x29, x29
000000000000665c	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006660	adrp	x8, 6 ; 0xc000
0000000000006664	ldr	x1, [x8, #0x160] ; Objc selector ref: URLWithString:
0000000000006668	ldr	x8, [sp, #0x48]
000000000000666c	str	x0, [sp, #0x40]
0000000000006670	mov	x0, x8
0000000000006674	ldr	x2, [sp, #0x40]
0000000000006678	bl	0x7918 ; Objc message: -[x0 URLWithString:]
000000000000667c	mov	x29, x29
0000000000006680	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006684	stur	x0, [x29, #-0x28]
0000000000006688	ldr	x0, [sp, #0x40]
000000000000668c	bl	0x7924 ; symbol stub for: _objc_release
0000000000006690	ldur	x8, [x29, #-0x28]
0000000000006694	cbnz	x8, 0x66a4
0000000000006698	mov	w8, #0x1
000000000000669c	stur	w8, [x29, #-0x2c]
00000000000066a0	b	0x67c8
00000000000066a4	adrp	x8, 6 ; 0xc000
00000000000066a8	ldr	x0, [x8, #0x308] ; Objc class ref: _OBJC_CLASS_$_NSURLSessionConfiguration
00000000000066ac	adrp	x8, 6 ; 0xc000
00000000000066b0	ldr	x1, [x8, #0x168] ; Objc selector ref: ephemeralSessionConfiguration
00000000000066b4	bl	0x7918 ; Objc message: +[NSURLSessionConfiguration ephemeralSessionConfiguration]
00000000000066b8	mov	x29, x29
00000000000066bc	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000066c0	sub	x8, x29, #0x38
00000000000066c4	stur	x0, [x29, #-0x38]
00000000000066c8	ldur	x0, [x29, #-0x38]
00000000000066cc	adrp	x9, 6 ; 0xc000
00000000000066d0	ldr	x1, [x9, #0x170] ; Objc selector ref: setTimeoutIntervalForRequest:
00000000000066d4	fmov	d0, #15.00000000
00000000000066d8	str	x8, [sp, #0x38]
00000000000066dc	bl	0x7918 ; Objc message: -[x0 setTimeoutIntervalForRequest:]
00000000000066e0	ldur	x0, [x29, #-0x38]
00000000000066e4	adrp	x8, 6 ; 0xc000
00000000000066e8	ldr	x1, [x8, #0x178] ; Objc selector ref: setRequestCachePolicy:
00000000000066ec	mov	x2, #0x4
00000000000066f0	bl	0x7918 ; Objc message: -[x0 setRequestCachePolicy:]
00000000000066f4	adrp	x8, 6 ; 0xc000
00000000000066f8	ldr	x0, [x8, #0x310] ; Objc class ref: _OBJC_CLASS_$_NSURLSession
00000000000066fc	ldur	x2, [x29, #-0x38]
0000000000006700	adrp	x8, 6 ; 0xc000
0000000000006704	ldr	x1, [x8, #0x180] ; Objc selector ref: sessionWithConfiguration:
0000000000006708	bl	0x7918 ; Objc message: +[NSURLSession sessionWithConfiguration:]
000000000000670c	mov	x29, x29
0000000000006710	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006714	sub	x8, x29, #0x40
0000000000006718	stur	x0, [x29, #-0x40]
000000000000671c	ldur	x0, [x29, #-0x40]
0000000000006720	ldur	x2, [x29, #-0x28]
0000000000006724	add	x9, sp, #0x70
0000000000006728	adrp	x10, 2 ; 0x8000
000000000000672c	ldr	x10, [x10, #0x8] ; literal pool symbol address: __NSConcreteStackBlock
0000000000006730	str	x10, [sp, #0x70]
0000000000006734	mov	w11, #-0x3e000000
0000000000006738	str	w11, [sp, #0x78]
000000000000673c	str	wzr, [sp, #0x7c]
0000000000006740	adrp	x10, 0 ; 0x6000
0000000000006744	add	x10, x10, #0x800
0000000000006748	str	x10, [x9, #0x10]
000000000000674c	adrp	x10, 2 ; 0x8000
0000000000006750	add	x10, x10, #0x88
0000000000006754	str	x10, [x9, #0x18]
0000000000006758	ldur	x10, [x29, #-0x8]
000000000000675c	str	x10, [x9, #0x20]
0000000000006760	adrp	x10, 6 ; 0xc000
0000000000006764	ldr	x1, [x10, #0x1c8] ; Objc selector ref: dataTaskWithURL:completionHandler:
0000000000006768	mov	x3, x9
000000000000676c	str	x8, [sp, #0x30]
0000000000006770	bl	0x7918 ; Objc message: -[x0 dataTaskWithURL:completionHandler:]
0000000000006774	mov	x29, x29
0000000000006778	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000677c	sub	x8, x29, #0x48
0000000000006780	stur	x0, [x29, #-0x48]
0000000000006784	ldur	x0, [x29, #-0x48]
0000000000006788	adrp	x9, 6 ; 0xc000
000000000000678c	ldr	x1, [x9, #0x1d0] ; Objc selector ref: resume
0000000000006790	str	x8, [sp, #0x28]
0000000000006794	bl	0x7918 ; Objc message: -[x0 resume]
0000000000006798	ldr	x0, [sp, #0x28]
000000000000679c	mov	x8, #0x0
00000000000067a0	mov	x1, x8
00000000000067a4	str	x8, [sp, #0x20]
00000000000067a8	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000067ac	ldr	x0, [sp, #0x30]
00000000000067b0	ldr	x1, [sp, #0x20]
00000000000067b4	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000067b8	ldr	x0, [sp, #0x38]
00000000000067bc	ldr	x1, [sp, #0x20]
00000000000067c0	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000067c4	stur	wzr, [x29, #-0x2c]
00000000000067c8	sub	x0, x29, #0x28
00000000000067cc	mov	x8, #0x0
00000000000067d0	mov	x1, x8
00000000000067d4	str	x8, [sp, #0x18]
00000000000067d8	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000067dc	sub	x0, x29, #0x20
00000000000067e0	ldr	x1, [sp, #0x18]
00000000000067e4	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000067e8	sub	x0, x29, #0x18
00000000000067ec	ldr	x1, [sp, #0x18]
00000000000067f0	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000067f4	ldp	x29, x30, [sp, #0xe0]
00000000000067f8	add	sp, sp, #0xf0
00000000000067fc	ret
___16+[QHGate verify]_block_invoke:
0000000000006800	sub	sp, sp, #0xe0
0000000000006804	stp	x29, x30, [sp, #0xd0]
0000000000006808	add	x29, sp, #0xd0
000000000000680c	stur	x0, [x29, #-0x8]
0000000000006810	sub	x8, x29, #0x10
0000000000006814	stur	xzr, [x29, #-0x10]
0000000000006818	stur	x0, [x29, #-0x60]
000000000000681c	mov	x0, x8
0000000000006820	str	x2, [sp, #0x68]
0000000000006824	str	x3, [sp, #0x60]
0000000000006828	bl	0x7954 ; symbol stub for: _objc_storeStrong
000000000000682c	sub	x0, x29, #0x18
0000000000006830	stur	xzr, [x29, #-0x18]
0000000000006834	ldr	x1, [sp, #0x68]
0000000000006838	bl	0x7954 ; symbol stub for: _objc_storeStrong
000000000000683c	sub	x0, x29, #0x20
0000000000006840	stur	xzr, [x29, #-0x20]
0000000000006844	ldr	x1, [sp, #0x60]
0000000000006848	bl	0x7954 ; symbol stub for: _objc_storeStrong
000000000000684c	ldur	x8, [x29, #-0x60]
0000000000006850	stur	x8, [x29, #-0x28]
0000000000006854	ldur	x9, [x29, #-0x20]
0000000000006858	cbz	x9, 0x6868
000000000000685c	mov	w8, #0x1
0000000000006860	stur	w8, [x29, #-0x2c]
0000000000006864	b	0x6afc
0000000000006868	mov	w8, #0x0
000000000000686c	sturb	w8, [x29, #-0x2d]
0000000000006870	stur	xzr, [x29, #-0x38]
0000000000006874	ldur	x9, [x29, #-0x10]
0000000000006878	cbz	x9, 0x6ac4
000000000000687c	adrp	x8, 6 ; 0xc000
0000000000006880	ldr	x0, [x8, #0x318] ; Objc class ref: _OBJC_CLASS_$_NSJSONSerialization
0000000000006884	ldur	x2, [x29, #-0x10]
0000000000006888	adrp	x8, 6 ; 0xc000
000000000000688c	ldr	x1, [x8, #0x188] ; Objc selector ref: JSONObjectWithData:options:error:
0000000000006890	mov	x8, #0x0
0000000000006894	mov	x3, x8
0000000000006898	mov	x8, #0x0
000000000000689c	mov	x4, x8
00000000000068a0	bl	0x7918 ; Objc message: +[NSJSONSerialization JSONObjectWithData:options:error:]
00000000000068a4	mov	x29, x29
00000000000068a8	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000068ac	stur	x0, [x29, #-0x40]
00000000000068b0	ldur	x0, [x29, #-0x40]
00000000000068b4	adrp	x8, 6 ; 0xc000
00000000000068b8	ldr	x8, [x8, #0x320] ; Objc class ref: _OBJC_CLASS_$_NSDictionary
00000000000068bc	adrp	x9, 6 ; 0xc000
00000000000068c0	ldr	x1, [x9, #0x190] ; Objc selector ref: class
00000000000068c4	str	x0, [sp, #0x58]
00000000000068c8	mov	x0, x8
00000000000068cc	bl	0x7918 ; Objc message: +[NSDictionary class]
00000000000068d0	adrp	x8, 6 ; 0xc000
00000000000068d4	ldr	x1, [x8, #0x198] ; Objc selector ref: isKindOfClass:
00000000000068d8	ldr	x8, [sp, #0x58]
00000000000068dc	str	x0, [sp, #0x50]
00000000000068e0	mov	x0, x8
00000000000068e4	ldr	x2, [sp, #0x50]
00000000000068e8	bl	0x7918 ; Objc message: -[x0 isKindOfClass:]
00000000000068ec	tbnz	w0, #0x0, 0x68f4
00000000000068f0	b	0x6ab4
00000000000068f4	ldur	x0, [x29, #-0x40]
00000000000068f8	adrp	x8, 6 ; 0xc000
00000000000068fc	ldr	x1, [x8, #0x1a0] ; Objc selector ref: objectForKeyedSubscript:
0000000000006900	adrp	x2, 2 ; 0x8000
0000000000006904	add	x2, x2, #0x188 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006908	str	x8, [sp, #0x48]
000000000000690c	bl	0x7918 ; Objc message: -[x0 objectForKeyedSubscript:]
0000000000006910	mov	x29, x29
0000000000006914	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006918	stur	x0, [x29, #-0x48]
000000000000691c	ldur	x0, [x29, #-0x40]
0000000000006920	ldr	x8, [sp, #0x48]
0000000000006924	ldr	x1, [x8, #0x1a0]
0000000000006928	adrp	x2, 2 ; 0x8000
000000000000692c	add	x2, x2, #0x1a8 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006930	bl	0x7918 ; Objc message: -[x0 objectForKeyedSubscript:]
0000000000006934	mov	x29, x29
0000000000006938	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000693c	stur	x0, [x29, #-0x50]
0000000000006940	ldur	x0, [x29, #-0x50]
0000000000006944	adrp	x8, 6 ; 0xc000
0000000000006948	ldr	x8, [x8, #0x300] ; Objc class ref: _OBJC_CLASS_$_NSString
000000000000694c	adrp	x9, 6 ; 0xc000
0000000000006950	ldr	x1, [x9, #0x190] ; Objc selector ref: class
0000000000006954	str	x0, [sp, #0x40]
0000000000006958	mov	x0, x8
000000000000695c	bl	0x7918 ; Objc message: +[NSString class]
0000000000006960	adrp	x8, 6 ; 0xc000
0000000000006964	ldr	x1, [x8, #0x198] ; Objc selector ref: isKindOfClass:
0000000000006968	ldr	x8, [sp, #0x40]
000000000000696c	str	x0, [sp, #0x38]
0000000000006970	mov	x0, x8
0000000000006974	ldr	x2, [sp, #0x38]
0000000000006978	bl	0x7918 ; Objc message: -[x0 isKindOfClass:]
000000000000697c	tbnz	w0, #0x0, 0x6984
0000000000006980	b	0x6990
0000000000006984	ldur	x1, [x29, #-0x50]
0000000000006988	sub	x0, x29, #0x38
000000000000698c	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006990	ldur	x8, [x29, #-0x48]
0000000000006994	cbz	x8, 0x6a94
0000000000006998	mov	w8, #0x0
000000000000699c	sturb	w8, [x29, #-0x51]
00000000000069a0	ldur	x0, [x29, #-0x48]
00000000000069a4	adrp	x9, 6 ; 0xc000
00000000000069a8	ldr	x9, [x9, #0x328] ; Objc class ref: _OBJC_CLASS_$_NSNumber
00000000000069ac	adrp	x10, 6 ; 0xc000
00000000000069b0	ldr	x1, [x10, #0x190] ; Objc selector ref: class
00000000000069b4	str	x0, [sp, #0x30]
00000000000069b8	mov	x0, x9
00000000000069bc	bl	0x7918 ; Objc message: +[NSNumber class]
00000000000069c0	adrp	x9, 6 ; 0xc000
00000000000069c4	ldr	x1, [x9, #0x198] ; Objc selector ref: isKindOfClass:
00000000000069c8	ldr	x9, [sp, #0x30]
00000000000069cc	str	x0, [sp, #0x28]
00000000000069d0	mov	x0, x9
00000000000069d4	ldr	x2, [sp, #0x28]
00000000000069d8	bl	0x7918 ; Objc message: -[x0 isKindOfClass:]
00000000000069dc	tbnz	w0, #0x0, 0x69e4
00000000000069e0	b	0x6a00
00000000000069e4	ldur	x0, [x29, #-0x48]
00000000000069e8	adrp	x8, 6 ; 0xc000
00000000000069ec	ldr	x1, [x8, #0x1a8] ; Objc selector ref: boolValue
00000000000069f0	bl	0x7918 ; Objc message: -[x0 boolValue]
00000000000069f4	and	w9, w0, #0x1
00000000000069f8	sturb	w9, [x29, #-0x51]
00000000000069fc	b	0x6a84
0000000000006a00	ldur	x0, [x29, #-0x48]
0000000000006a04	adrp	x8, 6 ; 0xc000
0000000000006a08	ldr	x8, [x8, #0x300] ; Objc class ref: _OBJC_CLASS_$_NSString
0000000000006a0c	adrp	x9, 6 ; 0xc000
0000000000006a10	ldr	x1, [x9, #0x190] ; Objc selector ref: class
0000000000006a14	str	x0, [sp, #0x20]
0000000000006a18	mov	x0, x8
0000000000006a1c	bl	0x7918 ; Objc message: +[NSString class]
0000000000006a20	adrp	x8, 6 ; 0xc000
0000000000006a24	ldr	x1, [x8, #0x198] ; Objc selector ref: isKindOfClass:
0000000000006a28	ldr	x8, [sp, #0x20]
0000000000006a2c	str	x0, [sp, #0x18]
0000000000006a30	mov	x0, x8
0000000000006a34	ldr	x2, [sp, #0x18]
0000000000006a38	bl	0x7918 ; Objc message: -[x0 isKindOfClass:]
0000000000006a3c	tbnz	w0, #0x0, 0x6a44
0000000000006a40	b	0x6a84
0000000000006a44	ldur	x0, [x29, #-0x48]
0000000000006a48	adrp	x8, 6 ; 0xc000
0000000000006a4c	ldr	x1, [x8, #0x1b0] ; Objc selector ref: lowercaseString
0000000000006a50	bl	0x7918 ; Objc message: -[x0 lowercaseString]
0000000000006a54	mov	x29, x29
0000000000006a58	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006a5c	adrp	x8, 6 ; 0xc000
0000000000006a60	ldr	x1, [x8, #0x1b8] ; Objc selector ref: isEqualToString:
0000000000006a64	str	x0, [sp, #0x10]
0000000000006a68	adrp	x2, 2 ; 0x8000
0000000000006a6c	add	x2, x2, #0x1c8 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006a70	bl	0x7918 ; Objc message: -[x0 isEqualToString:]
0000000000006a74	and	w9, w0, #0x1
0000000000006a78	sturb	w9, [x29, #-0x51]
0000000000006a7c	ldr	x0, [sp, #0x10]
0000000000006a80	bl	0x7924 ; symbol stub for: _objc_release
0000000000006a84	ldurb	w8, [x29, #-0x51]
0000000000006a88	tbnz	w8, #0x0, 0x6a94
0000000000006a8c	mov	w8, #0x1
0000000000006a90	sturb	w8, [x29, #-0x2d]
0000000000006a94	sub	x0, x29, #0x50
0000000000006a98	mov	x8, #0x0
0000000000006a9c	mov	x1, x8
0000000000006aa0	str	x8, [sp, #0x8]
0000000000006aa4	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006aa8	sub	x0, x29, #0x48
0000000000006aac	ldr	x1, [sp, #0x8]
0000000000006ab0	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006ab4	sub	x0, x29, #0x40
0000000000006ab8	mov	x8, #0x0
0000000000006abc	mov	x1, x8
0000000000006ac0	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006ac4	ldurb	w8, [x29, #-0x2d]
0000000000006ac8	tbnz	w8, #0x0, 0x6ad0
0000000000006acc	b	0x6ae8
0000000000006ad0	ldur	x8, [x29, #-0x60]
0000000000006ad4	ldr	x0, [x8, #0x20]
0000000000006ad8	ldur	x2, [x29, #-0x38]
0000000000006adc	adrp	x9, 6 ; 0xc000
0000000000006ae0	ldr	x1, [x9, #0x1c0] ; Objc selector ref: blockOnMain:
0000000000006ae4	bl	0x7918 ; Objc message: -[x0 blockOnMain:]
0000000000006ae8	sub	x0, x29, #0x38
0000000000006aec	mov	x8, #0x0
0000000000006af0	mov	x1, x8
0000000000006af4	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006af8	stur	wzr, [x29, #-0x2c]
0000000000006afc	sub	x0, x29, #0x20
0000000000006b00	mov	x8, #0x0
0000000000006b04	mov	x1, x8
0000000000006b08	str	x8, [sp]
0000000000006b0c	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006b10	sub	x0, x29, #0x18
0000000000006b14	ldr	x1, [sp]
0000000000006b18	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006b1c	sub	x0, x29, #0x10
0000000000006b20	ldr	x1, [sp]
0000000000006b24	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006b28	ldp	x29, x30, [sp, #0xd0]
0000000000006b2c	add	sp, sp, #0xe0
0000000000006b30	ret
___copy_helper_block_e8_:
0000000000006b34	sub	sp, sp, #0x10
0000000000006b38	str	x0, [sp, #0x8]
0000000000006b3c	str	x1, [sp]
0000000000006b40	add	sp, sp, #0x10
0000000000006b44	ret
___destroy_helper_block_e8_:
0000000000006b48	sub	sp, sp, #0x10
0000000000006b4c	str	x0, [sp, #0x8]
0000000000006b50	add	sp, sp, #0x10
0000000000006b54	ret
+[QHGate blockOnMain:]:
0000000000006b58	sub	sp, sp, #0x80
0000000000006b5c	stp	x29, x30, [sp, #0x70]
0000000000006b60	add	x29, sp, #0x70
0000000000006b64	stur	x0, [x29, #-0x8]
0000000000006b68	stur	x1, [x29, #-0x10]
0000000000006b6c	sub	x8, x29, #0x18
0000000000006b70	mov	x9, #0x0
0000000000006b74	stur	xzr, [x29, #-0x18]
0000000000006b78	mov	x0, x8
0000000000006b7c	mov	x1, x2
0000000000006b80	str	x8, [sp, #0x20]
0000000000006b84	str	x9, [sp, #0x18]
0000000000006b88	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006b8c	adrp	x0, 2 ; 0x8000
0000000000006b90	ldr	x0, [x0, #0x18] ; literal pool symbol address: __dispatch_main_q
0000000000006b94	bl	0x793c ; symbol stub for: _objc_retainAutoreleaseReturnValue
0000000000006b98	mov	x29, x29
0000000000006b9c	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006ba0	add	x8, sp, #0x28
0000000000006ba4	adrp	x9, 2 ; 0x8000
0000000000006ba8	ldr	x9, [x9, #0x8] ; literal pool symbol address: __NSConcreteStackBlock
0000000000006bac	str	x9, [sp, #0x28]
0000000000006bb0	mov	w10, #-0x3e000000
0000000000006bb4	str	w10, [sp, #0x30]
0000000000006bb8	str	wzr, [sp, #0x34]
0000000000006bbc	adrp	x9, 0 ; 0x6000
0000000000006bc0	add	x9, x9, #0xc38
0000000000006bc4	str	x9, [x8, #0x10]
0000000000006bc8	adrp	x9, 2 ; 0x8000
0000000000006bcc	add	x9, x9, #0xb8
0000000000006bd0	str	x9, [x8, #0x18]
0000000000006bd4	ldur	x9, [x29, #-0x8]
0000000000006bd8	str	x9, [x8, #0x20]
0000000000006bdc	add	x9, x8, #0x28
0000000000006be0	ldur	x11, [x29, #-0x18]
0000000000006be4	str	x0, [sp, #0x10]
0000000000006be8	mov	x0, x11
0000000000006bec	str	x8, [sp, #0x8]
0000000000006bf0	str	x9, [sp]
0000000000006bf4	bl	0x7930 ; symbol stub for: _objc_retain
0000000000006bf8	ldr	x8, [sp, #0x8]
0000000000006bfc	str	x0, [x8, #0x28]
0000000000006c00	ldr	x0, [sp, #0x10]
0000000000006c04	mov	x1, x8
0000000000006c08	bl	0x78d0 ; symbol stub for: _dispatch_async
0000000000006c0c	ldr	x0, [sp, #0x10]
0000000000006c10	bl	0x7924 ; symbol stub for: _objc_release
0000000000006c14	ldr	x0, [sp]
0000000000006c18	ldr	x1, [sp, #0x18]
0000000000006c1c	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006c20	ldr	x0, [sp, #0x20]
0000000000006c24	ldr	x1, [sp, #0x18]
0000000000006c28	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006c2c	ldp	x29, x30, [sp, #0x70]
0000000000006c30	add	sp, sp, #0x80
0000000000006c34	ret
___22+[QHGate blockOnMain:]_block_invoke:
0000000000006c38	sub	sp, sp, #0x20
0000000000006c3c	stp	x29, x30, [sp, #0x10]
0000000000006c40	add	x29, sp, #0x10
0000000000006c44	str	x0, [sp, #0x8]
0000000000006c48	str	x0, [sp]
0000000000006c4c	ldr	x8, [x0, #0x20]
0000000000006c50	ldr	x2, [x0, #0x28]
0000000000006c54	adrp	x9, 6 ; 0xc000
0000000000006c58	ldr	x1, [x9, #0x1d8] ; Objc selector ref: blockWithMessage:
0000000000006c5c	mov	x0, x8
0000000000006c60	bl	0x7918 ; Objc message: -[x0 blockWithMessage:]
0000000000006c64	ldp	x29, x30, [sp, #0x10]
0000000000006c68	add	sp, sp, #0x20
0000000000006c6c	ret
___copy_helper_block_e8_40s:
0000000000006c70	sub	sp, sp, #0x20
0000000000006c74	stp	x29, x30, [sp, #0x10]
0000000000006c78	add	x29, sp, #0x10
0000000000006c7c	str	x0, [sp, #0x8]
0000000000006c80	str	x1, [sp]
0000000000006c84	ldr	x8, [sp]
0000000000006c88	ldr	x9, [sp, #0x8]
0000000000006c8c	add	x0, x9, #0x28
0000000000006c90	ldr	x1, [x8, #0x28]
0000000000006c94	str	xzr, [x9, #0x28]
0000000000006c98	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006c9c	ldp	x29, x30, [sp, #0x10]
0000000000006ca0	add	sp, sp, #0x20
0000000000006ca4	ret
___destroy_helper_block_e8_40s:
0000000000006ca8	sub	sp, sp, #0x20
0000000000006cac	stp	x29, x30, [sp, #0x10]
0000000000006cb0	add	x29, sp, #0x10
0000000000006cb4	str	x0, [sp, #0x8]
0000000000006cb8	ldr	x8, [sp, #0x8]
0000000000006cbc	add	x0, x8, #0x28
0000000000006cc0	mov	x8, #0x0
0000000000006cc4	mov	x1, x8
0000000000006cc8	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006ccc	ldp	x29, x30, [sp, #0x10]
0000000000006cd0	add	sp, sp, #0x20
0000000000006cd4	ret
+[QHGate blockWithMessage:]:
0000000000006cd8	stp	x28, x27, [sp, #-0x20]!
0000000000006cdc	stp	x29, x30, [sp, #0x10]
0000000000006ce0	add	x29, sp, #0x10
0000000000006ce4	sub	sp, sp, #0x480
0000000000006ce8	mov	w8, #0x1
0000000000006cec	adrp	x9, 2 ; 0x8000
0000000000006cf0	ldr	x9, [x9, #0x10] ; literal pool symbol address: ___stack_chk_guard
0000000000006cf4	ldr	x9, [x9]
0000000000006cf8	stur	x9, [x29, #-0x18]
0000000000006cfc	str	x0, [sp, #0x350]
0000000000006d00	str	x1, [sp, #0x348]
0000000000006d04	add	x0, sp, #0x340
0000000000006d08	str	xzr, [sp, #0x340]
0000000000006d0c	mov	x1, x2
0000000000006d10	str	w8, [sp, #0x22c]
0000000000006d14	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006d18	adrp	x9, 6 ; 0xc000
0000000000006d1c	ldr	x9, [x9, #0x3d8]
0000000000006d20	cbz	x9, 0x6d30
0000000000006d24	mov	w8, #0x1
0000000000006d28	str	w8, [sp, #0x33c]
0000000000006d2c	b	0x7844
0000000000006d30	ldr	x0, [sp, #0x340]
0000000000006d34	adrp	x8, 6 ; 0xc000
0000000000006d38	ldr	x1, [x8, #0x130] ; Objc selector ref: length
0000000000006d3c	bl	0x7918 ; Objc message: -[x0 length]
0000000000006d40	cmp	x0, #0x0
0000000000006d44	cset	w9, ls
0000000000006d48	tbnz	w9, #0x0, 0x6d58
0000000000006d4c	ldr	x8, [sp, #0x340]
0000000000006d50	str	x8, [sp, #0x220]
0000000000006d54	b	0x6d64
0000000000006d58	adrp	x8, 2 ; 0x8000
0000000000006d5c	add	x8, x8, #0x148 ; Objc cfstring ref: @"bad cfstring ref"
0000000000006d60	str	x8, [sp, #0x220]
0000000000006d64	ldr	x8, [sp, #0x220]
0000000000006d68	mov	x0, x8
0000000000006d6c	bl	0x7930 ; symbol stub for: _objc_retain
0000000000006d70	str	x0, [sp, #0x330]
0000000000006d74	str	xzr, [sp, #0x328]
0000000000006d78	ldr	w9, [sp, #0x22c]
0000000000006d7c	tbnz	w9, #0x0, 0x6d84
0000000000006d80	b	0x720c
0000000000006d84	str	xzr, [sp, #0x320]
0000000000006d88	add	x8, sp, #0x2d8
0000000000006d8c	mov	x0, x8
0000000000006d90	mov	w9, #0x0
0000000000006d94	mov	x1, x9
0000000000006d98	mov	x2, #0x40
0000000000006d9c	str	x8, [sp, #0x218]
0000000000006da0	bl	0x78e8 ; symbol stub for: _memset
0000000000006da4	adrp	x8, 6 ; 0xc000
0000000000006da8	ldr	x0, [x8, #0x330] ; Objc class ref: _OBJC_CLASS_$_UIApplication
0000000000006dac	adrp	x8, 6 ; 0xc000
0000000000006db0	ldr	x1, [x8, #0x1e0] ; Objc selector ref: sharedApplication
0000000000006db4	bl	0x7918 ; Objc message: +[UIApplication sharedApplication]
0000000000006db8	mov	x29, x29
0000000000006dbc	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006dc0	adrp	x8, 6 ; 0xc000
0000000000006dc4	ldr	x1, [x8, #0x1e8] ; Objc selector ref: connectedScenes
0000000000006dc8	str	x0, [sp, #0x210]
0000000000006dcc	bl	0x7918 ; Objc message: -[x0 connectedScenes]
0000000000006dd0	mov	x29, x29
0000000000006dd4	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006dd8	ldr	x1, [sp, #0x210]
0000000000006ddc	str	x0, [sp, #0x208]
0000000000006de0	mov	x0, x1
0000000000006de4	bl	0x7924 ; symbol stub for: _objc_release
0000000000006de8	adrp	x8, 6 ; 0xc000
0000000000006dec	ldr	x1, [x8, #0x1f0] ; Objc selector ref: countByEnumeratingWithState:objects:count:
0000000000006df0	ldr	x0, [sp, #0x208]
0000000000006df4	ldr	x2, [sp, #0x218]
0000000000006df8	sub	x3, x29, #0x98
0000000000006dfc	mov	x4, #0x10
0000000000006e00	bl	0x7918 ; Objc message: -[x0 countByEnumeratingWithState:objects:count:]
0000000000006e04	str	x0, [sp, #0x200]
0000000000006e08	cbz	x0, 0x6f20
0000000000006e0c	mov	x8, #0x0
0000000000006e10	ldr	x9, [sp, #0x2e8]
0000000000006e14	ldr	x9, [x9]
0000000000006e18	ldr	x10, [sp, #0x200]
0000000000006e1c	str	x9, [sp, #0x1f8]
0000000000006e20	str	x8, [sp, #0x1f0]
0000000000006e24	str	x10, [sp, #0x1e8]
0000000000006e28	ldr	x0, [sp, #0x1e8]
0000000000006e2c	ldr	x8, [sp, #0x1f0]
0000000000006e30	ldr	x9, [sp, #0x2e8]
0000000000006e34	ldr	x9, [x9]
0000000000006e38	ldr	x10, [sp, #0x1f8]
0000000000006e3c	subs	x9, x9, x10
0000000000006e40	str	x0, [sp, #0x1e0]
0000000000006e44	str	x8, [sp, #0x1d8]
0000000000006e48	b.eq	0x6e54
0000000000006e4c	ldr	x0, [sp, #0x208]
0000000000006e50	bl	0x790c ; symbol stub for: _objc_enumerationMutation
0000000000006e54	ldr	x8, [sp, #0x2e0]
0000000000006e58	ldr	x9, [sp, #0x1d8]
0000000000006e5c	ldr	x8, [x8, x9, lsl #3]
0000000000006e60	str	x8, [sp, #0x318]
0000000000006e64	ldr	x0, [sp, #0x318]
0000000000006e68	adrp	x8, 6 ; 0xc000
0000000000006e6c	ldr	x8, [x8, #0x338] ; Objc class ref: _OBJC_CLASS_$_UIWindowScene
0000000000006e70	adrp	x10, 6 ; 0xc000
0000000000006e74	ldr	x1, [x10, #0x190] ; Objc selector ref: class
0000000000006e78	str	x0, [sp, #0x1d0]
0000000000006e7c	mov	x0, x8
0000000000006e80	bl	0x7918 ; Objc message: +[UIWindowScene class]
0000000000006e84	adrp	x8, 6 ; 0xc000
0000000000006e88	ldr	x1, [x8, #0x198] ; Objc selector ref: isKindOfClass:
0000000000006e8c	ldr	x8, [sp, #0x1d0]
0000000000006e90	str	x0, [sp, #0x1c8]
0000000000006e94	mov	x0, x8
0000000000006e98	ldr	x2, [sp, #0x1c8]
0000000000006e9c	bl	0x7918 ; Objc message: -[x0 isKindOfClass:]
0000000000006ea0	tbnz	w0, #0x0, 0x6ea8
0000000000006ea4	b	0x6ed4
0000000000006ea8	ldr	x0, [sp, #0x318]
0000000000006eac	adrp	x8, 6 ; 0xc000
0000000000006eb0	ldr	x1, [x8, #0x1f8] ; Objc selector ref: activationState
0000000000006eb4	bl	0x7918 ; Objc message: -[x0 activationState]
0000000000006eb8	cbnz	x0, 0x6ed4
0000000000006ebc	ldr	x1, [sp, #0x318]
0000000000006ec0	add	x0, sp, #0x320
0000000000006ec4	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000006ec8	mov	w8, #0x2
0000000000006ecc	str	w8, [sp, #0x33c]
0000000000006ed0	b	0x6f24
0000000000006ed4	ldr	x8, [sp, #0x1d8]
0000000000006ed8	add	x9, x8, #0x1
0000000000006edc	ldr	x10, [sp, #0x1e0]
0000000000006ee0	subs	x11, x9, x10
0000000000006ee4	str	x9, [sp, #0x1f0]
0000000000006ee8	str	x10, [sp, #0x1e8]
0000000000006eec	b.lo	0x6e28
0000000000006ef0	adrp	x8, 6 ; 0xc000
0000000000006ef4	ldr	x1, [x8, #0x1f0] ; Objc selector ref: countByEnumeratingWithState:objects:count:
0000000000006ef8	ldr	x0, [sp, #0x208]
0000000000006efc	add	x2, sp, #0x2d8
0000000000006f00	sub	x3, x29, #0x98
0000000000006f04	mov	x4, #0x10
0000000000006f08	bl	0x7918 ; Objc message: -[x0 countByEnumeratingWithState:objects:count:]
0000000000006f0c	mov	x8, #0x0
0000000000006f10	mov	x9, x0
0000000000006f14	str	x8, [sp, #0x1f0]
0000000000006f18	str	x9, [sp, #0x1e8]
0000000000006f1c	cbnz	x0, 0x6e28
0000000000006f20	str	wzr, [sp, #0x33c]
0000000000006f24	ldr	x0, [sp, #0x208]
0000000000006f28	bl	0x7924 ; symbol stub for: _objc_release
0000000000006f2c	ldr	w8, [sp, #0x33c]
0000000000006f30	cbz	w8, 0x6f3c
0000000000006f34	b	0x6f38
0000000000006f38	b	0x6f3c
0000000000006f3c	ldr	x8, [sp, #0x320]
0000000000006f40	cbnz	x8, 0x70e4
0000000000006f44	add	x8, sp, #0x290
0000000000006f48	mov	x0, x8
0000000000006f4c	mov	w9, #0x0
0000000000006f50	mov	x1, x9
0000000000006f54	mov	x2, #0x40
0000000000006f58	str	x8, [sp, #0x1c0]
0000000000006f5c	bl	0x78e8 ; symbol stub for: _memset
0000000000006f60	adrp	x8, 6 ; 0xc000
0000000000006f64	ldr	x0, [x8, #0x330] ; Objc class ref: _OBJC_CLASS_$_UIApplication
0000000000006f68	adrp	x8, 6 ; 0xc000
0000000000006f6c	ldr	x1, [x8, #0x1e0] ; Objc selector ref: sharedApplication
0000000000006f70	bl	0x7918 ; Objc message: +[UIApplication sharedApplication]
0000000000006f74	mov	x29, x29
0000000000006f78	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006f7c	adrp	x8, 6 ; 0xc000
0000000000006f80	ldr	x1, [x8, #0x1e8] ; Objc selector ref: connectedScenes
0000000000006f84	str	x0, [sp, #0x1b8]
0000000000006f88	bl	0x7918 ; Objc message: -[x0 connectedScenes]
0000000000006f8c	mov	x29, x29
0000000000006f90	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000006f94	ldr	x1, [sp, #0x1b8]
0000000000006f98	str	x0, [sp, #0x1b0]
0000000000006f9c	mov	x0, x1
0000000000006fa0	bl	0x7924 ; symbol stub for: _objc_release
0000000000006fa4	adrp	x8, 6 ; 0xc000
0000000000006fa8	ldr	x1, [x8, #0x1f0] ; Objc selector ref: countByEnumeratingWithState:objects:count:
0000000000006fac	ldr	x0, [sp, #0x1b0]
0000000000006fb0	ldr	x2, [sp, #0x1c0]
0000000000006fb4	add	x3, sp, #0x378
0000000000006fb8	mov	x4, #0x10
0000000000006fbc	bl	0x7918 ; Objc message: -[x0 countByEnumeratingWithState:objects:count:]
0000000000006fc0	str	x0, [sp, #0x1a8]
0000000000006fc4	cbz	x0, 0x70c8
0000000000006fc8	mov	x8, #0x0
0000000000006fcc	ldr	x9, [sp, #0x2a0]
0000000000006fd0	ldr	x9, [x9]
0000000000006fd4	ldr	x10, [sp, #0x1a8]
0000000000006fd8	str	x9, [sp, #0x1a0]
0000000000006fdc	str	x8, [sp, #0x198]
0000000000006fe0	str	x10, [sp, #0x190]
0000000000006fe4	ldr	x0, [sp, #0x190]
0000000000006fe8	ldr	x8, [sp, #0x198]
0000000000006fec	ldr	x9, [sp, #0x2a0]
0000000000006ff0	ldr	x9, [x9]
0000000000006ff4	ldr	x10, [sp, #0x1a0]
0000000000006ff8	subs	x9, x9, x10
0000000000006ffc	str	x0, [sp, #0x188]
0000000000007000	str	x8, [sp, #0x180]
0000000000007004	b.eq	0x7010
0000000000007008	ldr	x0, [sp, #0x1b0]
000000000000700c	bl	0x790c ; symbol stub for: _objc_enumerationMutation
0000000000007010	ldr	x8, [sp, #0x298]
0000000000007014	ldr	x9, [sp, #0x180]
0000000000007018	ldr	x8, [x8, x9, lsl #3]
000000000000701c	str	x8, [sp, #0x2d0]
0000000000007020	ldr	x0, [sp, #0x2d0]
0000000000007024	adrp	x8, 5 ; 0xc000
0000000000007028	ldr	x8, [x8, #0x338] ; Objc class ref: _OBJC_CLASS_$_UIWindowScene
000000000000702c	adrp	x10, 5 ; 0xc000
0000000000007030	ldr	x1, [x10, #0x190] ; Objc selector ref: class
0000000000007034	str	x0, [sp, #0x178]
0000000000007038	mov	x0, x8
000000000000703c	bl	0x7918 ; Objc message: +[UIWindowScene class]
0000000000007040	adrp	x8, 5 ; 0xc000
0000000000007044	ldr	x1, [x8, #0x198] ; Objc selector ref: isKindOfClass:
0000000000007048	ldr	x8, [sp, #0x178]
000000000000704c	str	x0, [sp, #0x170]
0000000000007050	mov	x0, x8
0000000000007054	ldr	x2, [sp, #0x170]
0000000000007058	bl	0x7918 ; Objc message: -[x0 isKindOfClass:]
000000000000705c	tbnz	w0, #0x0, 0x7064
0000000000007060	b	0x707c
0000000000007064	ldr	x1, [sp, #0x2d0]
0000000000007068	add	x0, sp, #0x320
000000000000706c	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007070	mov	w8, #0x4
0000000000007074	str	w8, [sp, #0x33c]
0000000000007078	b	0x70cc
000000000000707c	ldr	x8, [sp, #0x180]
0000000000007080	add	x9, x8, #0x1
0000000000007084	ldr	x10, [sp, #0x188]
0000000000007088	subs	x11, x9, x10
000000000000708c	str	x9, [sp, #0x198]
0000000000007090	str	x10, [sp, #0x190]
0000000000007094	b.lo	0x6fe4
0000000000007098	adrp	x8, 5 ; 0xc000
000000000000709c	ldr	x1, [x8, #0x1f0] ; Objc selector ref: countByEnumeratingWithState:objects:count:
00000000000070a0	ldr	x0, [sp, #0x1b0]
00000000000070a4	add	x2, sp, #0x290
00000000000070a8	add	x3, sp, #0x378
00000000000070ac	mov	x4, #0x10
00000000000070b0	bl	0x7918 ; Objc message: -[x0 countByEnumeratingWithState:objects:count:]
00000000000070b4	mov	x8, #0x0
00000000000070b8	mov	x9, x0
00000000000070bc	str	x8, [sp, #0x198]
00000000000070c0	str	x9, [sp, #0x190]
00000000000070c4	cbnz	x0, 0x6fe4
00000000000070c8	str	wzr, [sp, #0x33c]
00000000000070cc	ldr	x0, [sp, #0x1b0]
00000000000070d0	bl	0x7924 ; symbol stub for: _objc_release
00000000000070d4	ldr	w8, [sp, #0x33c]
00000000000070d8	cbz	w8, 0x70e4
00000000000070dc	b	0x70e0
00000000000070e0	b	0x70e4
00000000000070e4	ldr	x8, [sp, #0x320]
00000000000070e8	cbnz	x8, 0x71b0
00000000000070ec	mov	x8, #0x0
00000000000070f0	mov	x0, x8
00000000000070f4	mov	x1, #0x6500
00000000000070f8	movk	x1, #0x1dcd, lsl #16
00000000000070fc	bl	0x78dc ; symbol stub for: _dispatch_time
0000000000007100	adrp	x8, 1 ; 0x8000
0000000000007104	ldr	x8, [x8, #0x18] ; literal pool symbol address: __dispatch_main_q
0000000000007108	str	x0, [sp, #0x168]
000000000000710c	mov	x0, x8
0000000000007110	bl	0x793c ; symbol stub for: _objc_retainAutoreleaseReturnValue
0000000000007114	mov	x29, x29
0000000000007118	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000711c	add	x8, sp, #0x260
0000000000007120	adrp	x9, 1 ; 0x8000
0000000000007124	ldr	x9, [x9, #0x8] ; literal pool symbol address: __NSConcreteStackBlock
0000000000007128	str	x9, [sp, #0x260]
000000000000712c	mov	w10, #-0x3e000000
0000000000007130	str	w10, [sp, #0x268]
0000000000007134	str	wzr, [sp, #0x26c]
0000000000007138	adrp	x9, 0 ; 0x7000
000000000000713c	add	x9, x9, #0x880
0000000000007140	str	x9, [x8, #0x10]
0000000000007144	adrp	x9, 1 ; 0x8000
0000000000007148	add	x9, x9, #0xb8
000000000000714c	str	x9, [x8, #0x18]
0000000000007150	ldr	x9, [sp, #0x350]
0000000000007154	str	x9, [x8, #0x20]
0000000000007158	add	x9, x8, #0x28
000000000000715c	ldr	x11, [sp, #0x340]
0000000000007160	str	x0, [sp, #0x160]
0000000000007164	mov	x0, x11
0000000000007168	str	x8, [sp, #0x158]
000000000000716c	str	x9, [sp, #0x150]
0000000000007170	bl	0x7930 ; symbol stub for: _objc_retain
0000000000007174	ldr	x8, [sp, #0x158]
0000000000007178	str	x0, [x8, #0x28]
000000000000717c	ldr	x0, [sp, #0x168]
0000000000007180	ldr	x1, [sp, #0x160]
0000000000007184	mov	x2, x8
0000000000007188	bl	0x78c4 ; symbol stub for: _dispatch_after
000000000000718c	ldr	x0, [sp, #0x160]
0000000000007190	bl	0x7924 ; symbol stub for: _objc_release
0000000000007194	mov	w10, #0x1
0000000000007198	str	w10, [sp, #0x33c]
000000000000719c	ldr	x0, [sp, #0x150]
00000000000071a0	mov	x8, #0x0
00000000000071a4	mov	x1, x8
00000000000071a8	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000071ac	b	0x71e0
00000000000071b0	adrp	x8, 5 ; 0xc000
00000000000071b4	ldr	x0, [x8, #0x340] ; Objc class ref: _OBJC_CLASS_$_UIWindow
00000000000071b8	bl	0x78f4 ; symbol stub for: _objc_alloc
00000000000071bc	ldr	x2, [sp, #0x320]
00000000000071c0	adrp	x8, 5 ; 0xc000
00000000000071c4	ldr	x1, [x8, #0x200] ; Objc selector ref: initWithWindowScene:
00000000000071c8	bl	0x7918 ; Objc message: +[UIWindow initWithWindowScene:]
00000000000071cc	ldr	x8, [sp, #0x328]
00000000000071d0	str	x0, [sp, #0x328]
00000000000071d4	mov	x0, x8
00000000000071d8	bl	0x7924 ; symbol stub for: _objc_release
00000000000071dc	str	wzr, [sp, #0x33c]
00000000000071e0	add	x0, sp, #0x320
00000000000071e4	mov	x8, #0x0
00000000000071e8	mov	x1, x8
00000000000071ec	bl	0x7954 ; symbol stub for: _objc_storeStrong
00000000000071f0	ldr	w9, [sp, #0x33c]
00000000000071f4	cmp	w9, #0x0
00000000000071f8	cset	w9, eq
00000000000071fc	eor	w9, w9, #0x1
0000000000007200	tbnz	w9, #0x0, 0x7824
0000000000007204	b	0x7208
0000000000007208	b	0x7294
000000000000720c	adrp	x8, 5 ; 0xc000
0000000000007210	ldr	x0, [x8, #0x340] ; Objc class ref: _OBJC_CLASS_$_UIWindow
0000000000007214	bl	0x78f4 ; symbol stub for: _objc_alloc
0000000000007218	adrp	x8, 5 ; 0xc000
000000000000721c	ldr	x8, [x8, #0x348] ; Objc class ref: _OBJC_CLASS_$_UIScreen
0000000000007220	adrp	x9, 5 ; 0xc000
0000000000007224	ldr	x1, [x9, #0x208] ; Objc selector ref: mainScreen
0000000000007228	str	x0, [sp, #0x148]
000000000000722c	mov	x0, x8
0000000000007230	bl	0x7918 ; Objc message: +[UIScreen mainScreen]
0000000000007234	mov	x29, x29
0000000000007238	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000723c	adrp	x8, 5 ; 0xc000
0000000000007240	ldr	x1, [x8, #0x210] ; Objc selector ref: bounds
0000000000007244	str	x0, [sp, #0x140]
0000000000007248	bl	0x7918 ; Objc message: -[x0 bounds]
000000000000724c	str	d0, [sp, #0x240]
0000000000007250	str	d1, [sp, #0x248]
0000000000007254	str	d2, [sp, #0x250]
0000000000007258	str	d3, [sp, #0x258]
000000000000725c	adrp	x8, 5 ; 0xc000
0000000000007260	ldr	x1, [x8, #0x218] ; Objc selector ref: initWithFrame:
0000000000007264	ldr	d0, [sp, #0x240]
0000000000007268	ldr	d1, [sp, #0x248]
000000000000726c	ldr	d2, [sp, #0x250]
0000000000007270	ldr	d3, [sp, #0x258]
0000000000007274	ldr	x0, [sp, #0x148]
0000000000007278	bl	0x7918 ; Objc message: -[x0 initWithFrame:]
000000000000727c	ldr	x8, [sp, #0x328]
0000000000007280	str	x0, [sp, #0x328]
0000000000007284	mov	x0, x8
0000000000007288	bl	0x7924 ; symbol stub for: _objc_release
000000000000728c	ldr	x0, [sp, #0x140]
0000000000007290	bl	0x7924 ; symbol stub for: _objc_release
0000000000007294	adrp	x8, 1 ; 0x8000
0000000000007298	ldr	x8, [x8] ; literal pool symbol address: _UIWindowLevelAlert
000000000000729c	ldr	d0, [x8]
00000000000072a0	adrp	x8, 0 ; 0x7000
00000000000072a4	ldr	d1, [x8, #0xa80]
00000000000072a8	fadd	d0, d0, d1
00000000000072ac	ldr	x0, [sp, #0x328]
00000000000072b0	adrp	x8, 5 ; 0xc000
00000000000072b4	ldr	x1, [x8, #0x220] ; Objc selector ref: setWindowLevel:
00000000000072b8	bl	0x7918 ; Objc message: -[x0 setWindowLevel:]
00000000000072bc	adrp	x8, 5 ; 0xc000
00000000000072c0	ldr	x0, [x8, #0x350] ; Objc class ref: _OBJC_CLASS_$_UIColor
00000000000072c4	adrp	x9, 5 ; 0xc000
00000000000072c8	ldr	x1, [x9, #0x228] ; Objc selector ref: blackColor
00000000000072cc	str	x8, [sp, #0x138]
00000000000072d0	str	x9, [sp, #0x130]
00000000000072d4	bl	0x7918 ; Objc message: +[UIColor blackColor]
00000000000072d8	mov	x29, x29
00000000000072dc	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000072e0	ldr	x8, [sp, #0x328]
00000000000072e4	adrp	x9, 5 ; 0xc000
00000000000072e8	ldr	x1, [x9, #0x230] ; Objc selector ref: setBackgroundColor:
00000000000072ec	str	x0, [sp, #0x128]
00000000000072f0	mov	x0, x8
00000000000072f4	ldr	x2, [sp, #0x128]
00000000000072f8	str	x9, [sp, #0x120]
00000000000072fc	bl	0x7918 ; Objc message: -[x0 setBackgroundColor:]
0000000000007300	ldr	x0, [sp, #0x128]
0000000000007304	bl	0x7924 ; symbol stub for: _objc_release
0000000000007308	adrp	x8, 5 ; 0xc000
000000000000730c	ldr	x0, [x8, #0x358] ; Objc class ref: _OBJC_CLASS_$_UIViewController
0000000000007310	adrp	x8, 5 ; 0xc000
0000000000007314	ldr	x1, [x8, #0x238] ; Objc selector ref: new
0000000000007318	str	x8, [sp, #0x118]
000000000000731c	bl	0x7918 ; Objc message: +[UIViewController new]
0000000000007320	add	x8, sp, #0x238
0000000000007324	str	x0, [sp, #0x238]
0000000000007328	ldr	x9, [sp, #0x138]
000000000000732c	ldr	x0, [x9, #0x350]
0000000000007330	ldr	x10, [sp, #0x130]
0000000000007334	ldr	x1, [x10, #0x228]
0000000000007338	str	x8, [sp, #0x110]
000000000000733c	bl	0x7918 ; Objc message: -[x0 new]
0000000000007340	mov	x29, x29
0000000000007344	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007348	ldr	x8, [sp, #0x238]
000000000000734c	adrp	x9, 5 ; 0xc000
0000000000007350	ldr	x1, [x9, #0x240] ; Objc selector ref: view
0000000000007354	str	x0, [sp, #0x108]
0000000000007358	mov	x0, x8
000000000000735c	str	x9, [sp, #0x100]
0000000000007360	bl	0x7918 ; Objc message: -[x0 view]
0000000000007364	mov	x29, x29
0000000000007368	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000736c	ldr	x8, [sp, #0x120]
0000000000007370	ldr	x1, [x8, #0x230]
0000000000007374	str	x0, [sp, #0xf8]
0000000000007378	ldr	x2, [sp, #0x108]
000000000000737c	bl	0x7918 ; Objc message: -[x0 view]
0000000000007380	ldr	x0, [sp, #0xf8]
0000000000007384	bl	0x7924 ; symbol stub for: _objc_release
0000000000007388	ldr	x0, [sp, #0x108]
000000000000738c	bl	0x7924 ; symbol stub for: _objc_release
0000000000007390	adrp	x8, 5 ; 0xc000
0000000000007394	ldr	x0, [x8, #0x360] ; Objc class ref: _OBJC_CLASS_$_UILabel
0000000000007398	ldr	x8, [sp, #0x118]
000000000000739c	ldr	x1, [x8, #0x238]
00000000000073a0	bl	0x7918 ; Objc message: +[UILabel view]
00000000000073a4	add	x8, sp, #0x230
00000000000073a8	str	x0, [sp, #0x230]
00000000000073ac	ldr	x2, [sp, #0x330]
00000000000073b0	ldr	x0, [sp, #0x230]
00000000000073b4	adrp	x9, 5 ; 0xc000
00000000000073b8	ldr	x1, [x9, #0x248] ; Objc selector ref: setText:
00000000000073bc	str	x8, [sp, #0xf0]
00000000000073c0	bl	0x7918 ; Objc message: -[x0 setText:]
00000000000073c4	ldr	x8, [sp, #0x138]
00000000000073c8	ldr	x0, [x8, #0x350]
00000000000073cc	adrp	x9, 5 ; 0xc000
00000000000073d0	ldr	x1, [x9, #0x250] ; Objc selector ref: whiteColor
00000000000073d4	bl	0x7918 ; Objc message: -[x0 whiteColor]
00000000000073d8	mov	x29, x29
00000000000073dc	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000073e0	ldr	x8, [sp, #0x230]
00000000000073e4	adrp	x9, 5 ; 0xc000
00000000000073e8	ldr	x1, [x9, #0x258] ; Objc selector ref: setTextColor:
00000000000073ec	str	x0, [sp, #0xe8]
00000000000073f0	mov	x0, x8
00000000000073f4	ldr	x2, [sp, #0xe8]
00000000000073f8	bl	0x7918 ; Objc message: -[x0 setTextColor:]
00000000000073fc	ldr	x0, [sp, #0xe8]
0000000000007400	bl	0x7924 ; symbol stub for: _objc_release
0000000000007404	adrp	x8, 5 ; 0xc000
0000000000007408	ldr	x0, [x8, #0x368] ; Objc class ref: _OBJC_CLASS_$_UIFont
000000000000740c	adrp	x8, 5 ; 0xc000
0000000000007410	ldr	x1, [x8, #0x260] ; Objc selector ref: boldSystemFontOfSize:
0000000000007414	fmov	d0, #20.00000000
0000000000007418	bl	0x7918 ; Objc message: +[UIFont boldSystemFontOfSize:]
000000000000741c	mov	x29, x29
0000000000007420	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007424	ldr	x8, [sp, #0x230]
0000000000007428	adrp	x9, 5 ; 0xc000
000000000000742c	ldr	x1, [x9, #0x268] ; Objc selector ref: setFont:
0000000000007430	str	x0, [sp, #0xe0]
0000000000007434	mov	x0, x8
0000000000007438	ldr	x2, [sp, #0xe0]
000000000000743c	bl	0x7918 ; Objc message: -[x0 setFont:]
0000000000007440	ldr	x0, [sp, #0xe0]
0000000000007444	bl	0x7924 ; symbol stub for: _objc_release
0000000000007448	ldr	x0, [sp, #0x230]
000000000000744c	adrp	x8, 5 ; 0xc000
0000000000007450	ldr	x1, [x8, #0x270] ; Objc selector ref: setNumberOfLines:
0000000000007454	mov	x8, #0x0
0000000000007458	mov	x2, x8
000000000000745c	bl	0x7918 ; Objc message: -[x0 setNumberOfLines:]
0000000000007460	ldr	x0, [sp, #0x230]
0000000000007464	adrp	x8, 5 ; 0xc000
0000000000007468	ldr	x1, [x8, #0x278] ; Objc selector ref: setTextAlignment:
000000000000746c	mov	x2, #0x1
0000000000007470	bl	0x7918 ; Objc message: -[x0 setTextAlignment:]
0000000000007474	ldr	x0, [sp, #0x230]
0000000000007478	adrp	x8, 5 ; 0xc000
000000000000747c	ldr	x1, [x8, #0x280] ; Objc selector ref: setTranslatesAutoresizingMaskIntoConstraints:
0000000000007480	mov	w11, #0x0
0000000000007484	and	w2, w11, #0x1
0000000000007488	bl	0x7918 ; Objc message: -[x0 setTranslatesAutoresizingMaskIntoConstraints:]
000000000000748c	ldr	x0, [sp, #0x238]
0000000000007490	ldr	x8, [sp, #0x100]
0000000000007494	ldr	x1, [x8, #0x240]
0000000000007498	bl	0x7918 ; Objc message: -[x0 setTranslatesAutoresizingMaskIntoConstraints:]
000000000000749c	mov	x29, x29
00000000000074a0	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000074a4	ldr	x2, [sp, #0x230]
00000000000074a8	adrp	x8, 5 ; 0xc000
00000000000074ac	ldr	x1, [x8, #0x288] ; Objc selector ref: addSubview:
00000000000074b0	str	x0, [sp, #0xd8]
00000000000074b4	bl	0x7918 ; Objc message: -[x0 addSubview:]
00000000000074b8	ldr	x0, [sp, #0xd8]
00000000000074bc	bl	0x7924 ; symbol stub for: _objc_release
00000000000074c0	adrp	x8, 5 ; 0xc000
00000000000074c4	ldr	x0, [x8, #0x370] ; Objc class ref: _OBJC_CLASS_$_NSLayoutConstraint
00000000000074c8	ldr	x8, [sp, #0x230]
00000000000074cc	adrp	x9, 5 ; 0xc000
00000000000074d0	ldr	x1, [x9, #0x290] ; Objc selector ref: centerXAnchor
00000000000074d4	str	x0, [sp, #0xd0]
00000000000074d8	mov	x0, x8
00000000000074dc	str	x9, [sp, #0xc8]
00000000000074e0	bl	0x7918 ; Objc message: +[NSLayoutConstraint centerXAnchor]
00000000000074e4	mov	x29, x29
00000000000074e8	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000074ec	ldr	x8, [sp, #0x238]
00000000000074f0	ldr	x9, [sp, #0x100]
00000000000074f4	ldr	x1, [x9, #0x240]
00000000000074f8	str	x0, [sp, #0xc0]
00000000000074fc	mov	x0, x8
0000000000007500	bl	0x7918 ; Objc message: -[x0 centerXAnchor]
0000000000007504	mov	x29, x29
0000000000007508	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000750c	ldr	x8, [sp, #0xc8]
0000000000007510	ldr	x1, [x8, #0x290]
0000000000007514	str	x0, [sp, #0xb8]
0000000000007518	bl	0x7918 ; Objc message: -[x0 centerXAnchor]
000000000000751c	mov	x29, x29
0000000000007520	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007524	adrp	x8, 5 ; 0xc000
0000000000007528	ldr	x1, [x8, #0x298] ; Objc selector ref: constraintEqualToAnchor:
000000000000752c	ldr	x2, [sp, #0xc0]
0000000000007530	str	x0, [sp, #0xb0]
0000000000007534	mov	x0, x2
0000000000007538	ldr	x2, [sp, #0xb0]
000000000000753c	str	x8, [sp, #0xa8]
0000000000007540	bl	0x7918 ; Objc message: -[x0 constraintEqualToAnchor:]
0000000000007544	mov	x29, x29
0000000000007548	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000754c	add	x8, sp, #0x358
0000000000007550	str	x0, [sp, #0x358]
0000000000007554	ldr	x9, [sp, #0x230]
0000000000007558	adrp	x10, 5 ; 0xc000
000000000000755c	ldr	x1, [x10, #0x2a0] ; Objc selector ref: centerYAnchor
0000000000007560	str	x0, [sp, #0xa0]
0000000000007564	mov	x0, x9
0000000000007568	str	x8, [sp, #0x98]
000000000000756c	str	x10, [sp, #0x90]
0000000000007570	bl	0x7918 ; Objc message: -[x0 centerYAnchor]
0000000000007574	mov	x29, x29
0000000000007578	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000757c	ldr	x8, [sp, #0x238]
0000000000007580	ldr	x9, [sp, #0x100]
0000000000007584	ldr	x1, [x9, #0x240]
0000000000007588	str	x0, [sp, #0x88]
000000000000758c	mov	x0, x8
0000000000007590	bl	0x7918 ; Objc message: -[x0 centerYAnchor]
0000000000007594	mov	x29, x29
0000000000007598	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000759c	ldr	x8, [sp, #0x90]
00000000000075a0	ldr	x1, [x8, #0x2a0]
00000000000075a4	str	x0, [sp, #0x80]
00000000000075a8	bl	0x7918 ; Objc message: -[x0 centerYAnchor]
00000000000075ac	mov	x29, x29
00000000000075b0	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000075b4	ldr	x8, [sp, #0xa8]
00000000000075b8	ldr	x1, [x8, #0x298]
00000000000075bc	ldr	x2, [sp, #0x88]
00000000000075c0	str	x0, [sp, #0x78]
00000000000075c4	mov	x0, x2
00000000000075c8	ldr	x2, [sp, #0x78]
00000000000075cc	bl	0x7918 ; Objc message: -[x0 centerYAnchor]
00000000000075d0	mov	x29, x29
00000000000075d4	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000075d8	ldr	x8, [sp, #0x98]
00000000000075dc	str	x0, [x8, #0x8]
00000000000075e0	ldr	x9, [sp, #0x230]
00000000000075e4	adrp	x10, 5 ; 0xc000
00000000000075e8	ldr	x1, [x10, #0x2a8] ; Objc selector ref: leadingAnchor
00000000000075ec	str	x0, [sp, #0x70]
00000000000075f0	mov	x0, x9
00000000000075f4	str	x10, [sp, #0x68]
00000000000075f8	bl	0x7918 ; Objc message: -[x0 leadingAnchor]
00000000000075fc	mov	x29, x29
0000000000007600	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007604	ldr	x8, [sp, #0x238]
0000000000007608	ldr	x9, [sp, #0x100]
000000000000760c	ldr	x1, [x9, #0x240]
0000000000007610	str	x0, [sp, #0x60]
0000000000007614	mov	x0, x8
0000000000007618	bl	0x7918 ; Objc message: -[x0 leadingAnchor]
000000000000761c	mov	x29, x29
0000000000007620	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007624	ldr	x8, [sp, #0x68]
0000000000007628	ldr	x1, [x8, #0x2a8]
000000000000762c	str	x0, [sp, #0x58]
0000000000007630	bl	0x7918 ; Objc message: -[x0 leadingAnchor]
0000000000007634	mov	x29, x29
0000000000007638	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
000000000000763c	adrp	x8, 5 ; 0xc000
0000000000007640	ldr	x1, [x8, #0x2b0] ; Objc selector ref: constraintEqualToAnchor:constant:
0000000000007644	ldr	x2, [sp, #0x60]
0000000000007648	str	x0, [sp, #0x50]
000000000000764c	mov	x0, x2
0000000000007650	ldr	x2, [sp, #0x50]
0000000000007654	fmov	d0, #24.00000000
0000000000007658	str	x8, [sp, #0x48]
000000000000765c	bl	0x7918 ; Objc message: -[x0 constraintEqualToAnchor:constant:]
0000000000007660	mov	x29, x29
0000000000007664	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007668	ldr	x8, [sp, #0x98]
000000000000766c	str	x0, [x8, #0x10]
0000000000007670	ldr	x9, [sp, #0x230]
0000000000007674	adrp	x10, 5 ; 0xc000
0000000000007678	ldr	x1, [x10, #0x2b8] ; Objc selector ref: trailingAnchor
000000000000767c	str	x0, [sp, #0x40]
0000000000007680	mov	x0, x9
0000000000007684	str	x10, [sp, #0x38]
0000000000007688	bl	0x7918 ; Objc message: -[x0 trailingAnchor]
000000000000768c	mov	x29, x29
0000000000007690	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007694	ldr	x8, [sp, #0x238]
0000000000007698	ldr	x9, [sp, #0x100]
000000000000769c	ldr	x1, [x9, #0x240]
00000000000076a0	str	x0, [sp, #0x30]
00000000000076a4	mov	x0, x8
00000000000076a8	bl	0x7918 ; Objc message: -[x0 trailingAnchor]
00000000000076ac	mov	x29, x29
00000000000076b0	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000076b4	ldr	x8, [sp, #0x38]
00000000000076b8	ldr	x1, [x8, #0x2b8]
00000000000076bc	str	x0, [sp, #0x28]
00000000000076c0	bl	0x7918 ; Objc message: -[x0 trailingAnchor]
00000000000076c4	mov	x29, x29
00000000000076c8	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000076cc	ldr	x8, [sp, #0x48]
00000000000076d0	ldr	x1, [x8, #0x2b0]
00000000000076d4	ldr	x2, [sp, #0x30]
00000000000076d8	str	x0, [sp, #0x20]
00000000000076dc	mov	x0, x2
00000000000076e0	ldr	x2, [sp, #0x20]
00000000000076e4	fmov	d0, #-24.00000000
00000000000076e8	bl	0x7918 ; Objc message: -[x0 trailingAnchor]
00000000000076ec	mov	x29, x29
00000000000076f0	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
00000000000076f4	ldr	x8, [sp, #0x98]
00000000000076f8	str	x0, [x8, #0x18]
00000000000076fc	adrp	x9, 5 ; 0xc000
0000000000007700	ldr	x9, [x9, #0x378] ; Objc class ref: _OBJC_CLASS_$_NSArray
0000000000007704	adrp	x10, 5 ; 0xc000
0000000000007708	ldr	x1, [x10, #0x2c0] ; Objc selector ref: arrayWithObjects:count:
000000000000770c	str	x0, [sp, #0x18]
0000000000007710	mov	x0, x9
0000000000007714	mov	x2, x8
0000000000007718	mov	x3, #0x4
000000000000771c	bl	0x7918 ; Objc message: +[NSArray arrayWithObjects:count:]
0000000000007720	mov	x29, x29
0000000000007724	bl	0x7948 ; symbol stub for: _objc_retainAutoreleasedReturnValue
0000000000007728	adrp	x8, 5 ; 0xc000
000000000000772c	ldr	x1, [x8, #0x2c8] ; Objc selector ref: activateConstraints:
0000000000007730	ldr	x8, [sp, #0xd0]
0000000000007734	str	x0, [sp, #0x10]
0000000000007738	mov	x0, x8
000000000000773c	ldr	x2, [sp, #0x10]
0000000000007740	bl	0x7918 ; Objc message: -[x0 activateConstraints:]
0000000000007744	ldr	x0, [sp, #0x10]
0000000000007748	bl	0x7924 ; symbol stub for: _objc_release
000000000000774c	ldr	x0, [sp, #0x18]
0000000000007750	bl	0x7924 ; symbol stub for: _objc_release
0000000000007754	ldr	x0, [sp, #0x20]
0000000000007758	bl	0x7924 ; symbol stub for: _objc_release
000000000000775c	ldr	x0, [sp, #0x28]
0000000000007760	bl	0x7924 ; symbol stub for: _objc_release
0000000000007764	ldr	x0, [sp, #0x30]
0000000000007768	bl	0x7924 ; symbol stub for: _objc_release
000000000000776c	ldr	x0, [sp, #0x40]
0000000000007770	bl	0x7924 ; symbol stub for: _objc_release
0000000000007774	ldr	x0, [sp, #0x50]
0000000000007778	bl	0x7924 ; symbol stub for: _objc_release
000000000000777c	ldr	x0, [sp, #0x58]
0000000000007780	bl	0x7924 ; symbol stub for: _objc_release
0000000000007784	ldr	x0, [sp, #0x60]
0000000000007788	bl	0x7924 ; symbol stub for: _objc_release
000000000000778c	ldr	x0, [sp, #0x70]
0000000000007790	bl	0x7924 ; symbol stub for: _objc_release
0000000000007794	ldr	x0, [sp, #0x78]
0000000000007798	bl	0x7924 ; symbol stub for: _objc_release
000000000000779c	ldr	x0, [sp, #0x80]
00000000000077a0	bl	0x7924 ; symbol stub for: _objc_release
00000000000077a4	ldr	x0, [sp, #0x88]
00000000000077a8	bl	0x7924 ; symbol stub for: _objc_release
00000000000077ac	ldr	x0, [sp, #0xa0]
00000000000077b0	bl	0x7924 ; symbol stub for: _objc_release
00000000000077b4	ldr	x0, [sp, #0xb0]
00000000000077b8	bl	0x7924 ; symbol stub for: _objc_release
00000000000077bc	ldr	x0, [sp, #0xb8]
00000000000077c0	bl	0x7924 ; symbol stub for: _objc_release
00000000000077c4	ldr	x0, [sp, #0xc0]
00000000000077c8	bl	0x7924 ; symbol stub for: _objc_release
00000000000077cc	ldr	x2, [sp, #0x238]
00000000000077d0	ldr	x0, [sp, #0x328]
00000000000077d4	adrp	x8, 5 ; 0xc000
00000000000077d8	ldr	x1, [x8, #0x2d0] ; Objc selector ref: setRootViewController:
00000000000077dc	bl	0x7918 ; Objc message: -[x0 setRootViewController:]
00000000000077e0	ldr	x0, [sp, #0x328]
00000000000077e4	adrp	x8, 5 ; 0xc000
00000000000077e8	ldr	x1, [x8, #0x2d8] ; Objc selector ref: makeKeyAndVisible
00000000000077ec	bl	0x7918 ; Objc message: -[x0 makeKeyAndVisible]
00000000000077f0	ldr	x1, [sp, #0x328]
00000000000077f4	adrp	x0, 5 ; 0xc000
00000000000077f8	add	x0, x0, #0x3d8
00000000000077fc	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007800	ldr	x0, [sp, #0xf0]
0000000000007804	mov	x8, #0x0
0000000000007808	mov	x1, x8
000000000000780c	str	x8, [sp, #0x8]
0000000000007810	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007814	ldr	x0, [sp, #0x110]
0000000000007818	ldr	x1, [sp, #0x8]
000000000000781c	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007820	str	wzr, [sp, #0x33c]
0000000000007824	add	x0, sp, #0x328
0000000000007828	mov	x8, #0x0
000000000000782c	mov	x1, x8
0000000000007830	str	x8, [sp]
0000000000007834	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007838	add	x0, sp, #0x330
000000000000783c	ldr	x1, [sp]
0000000000007840	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007844	add	x0, sp, #0x340
0000000000007848	mov	x8, #0x0
000000000000784c	mov	x1, x8
0000000000007850	bl	0x7954 ; symbol stub for: _objc_storeStrong
0000000000007854	adrp	x8, 1 ; 0x8000
0000000000007858	ldr	x8, [x8, #0x10] ; literal pool symbol address: ___stack_chk_guard
000000000000785c	ldr	x8, [x8]
0000000000007860	ldur	x9, [x29, #-0x18]
0000000000007864	subs	x8, x8, x9
0000000000007868	b.ne	0x787c
000000000000786c	add	sp, sp, #0x480
0000000000007870	ldp	x29, x30, [sp, #0x10]
0000000000007874	ldp	x28, x27, [sp], #0x20
0000000000007878	ret
000000000000787c	bl	0x78b8 ; symbol stub for: ___stack_chk_fail
___27+[QHGate blockWithMessage:]_block_invoke:
0000000000007880	sub	sp, sp, #0x20
0000000000007884	stp	x29, x30, [sp, #0x10]
0000000000007888	add	x29, sp, #0x10
000000000000788c	str	x0, [sp, #0x8]
0000000000007890	str	x0, [sp]
0000000000007894	ldr	x8, [x0, #0x20]
0000000000007898	ldr	x2, [x0, #0x28]
000000000000789c	adrp	x9, 5 ; 0xc000
00000000000078a0	ldr	x1, [x9, #0x1d8] ; Objc selector ref: blockWithMessage:
00000000000078a4	mov	x0, x8
00000000000078a8	bl	0x7918 ; Objc message: -[x0 blockWithMessage:]
00000000000078ac	ldp	x29, x30, [sp, #0x10]
00000000000078b0	add	sp, sp, #0x20
00000000000078b4	ret
