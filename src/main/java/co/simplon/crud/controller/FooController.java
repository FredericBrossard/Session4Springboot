package co.simplon.crud.controller;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.simplon.crud.model.Foo;
import co.simplon.crud.service.IFooService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/foos")
class FooController {

	@Inject
	IFooService fooService;

	/*
	 * GET C'est la méthode la plus courante pour demander une ressource. Une
	 * requête GET est sans effet sur la ressource, il doit être possible de répéter
	 * la requête sans effet
	 */
	
/*	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Foo> findAll() {

		List<Foo> list = new ArrayList<>();

		Foo instance = new Foo("Fred");
		list.add(instance);

		Foo instanceBis = new Foo("Tropnul");
		list.add(instanceBis);

		Foo instanceBisBis = new Foo("Troptropnul");
		list.add(instanceBisBis);

		return list;
	}*/

	@RequestMapping(value = "/creatcookie", method = RequestMethod.GET)
	public static void addCookie(HttpServletResponse reponse) {
		Cookie cookie = new Cookie("name", "cookiefred");
		reponse.addCookie(cookie);
	}
	
	@GetMapping("/readcookie")
	@ResponseBody
	public String handle(@CookieValue("name") String cookie) {
		System.out.println(cookie);
		return cookie;
	}

	/*
	 * POST Cette méthode doit être utilisée lorsqu'une requête modifie la
	 * ressource.
	 */
	/*@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	// Methode create qui a un argument de type Foo. l'argument est un objet.
	public Long create(@RequestBody Foo resource) {
		System.out.println("tentative:" + resource);
		return 1L;
	}*/
	
	// *********************************debut*******************************************
	
	/* CODE JavaScript à lancer depuis la console HTML
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("POST", "/foos/");
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send(JSON.stringify({name:"John Rambo"}));

	xmlhttp.response
	*/
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Long createfoo(@RequestBody Foo foo) {
		Foo resp = fooService.saveFoo(foo);
		 System.out.println(resp.getName());
		return resp.getId();
	}
	
	/* CODE JavaScript à lancer depuis la console HTML
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.open("DELETE", "/foos/");
	xmlhttp.setRequestHeader("Content-Type", "application/json");
	xmlhttp.send(JSON.stringify({id:5}));

	xmlhttp.response
	*/
	//Le resultat de cette affaire la dessous, c'est qu il y a delete du nom mais pas de la ligne d'id 5
	//@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@RequestMapping(method = RequestMethod.DELETE)
	@ResponseBody
	//public void deleteFoo(@PathVariable(value= "id") Long id) {
	public void deleteFoo(@RequestBody Long id) {
		System.out.println("Entrée dans méthode delete avec id = " + id);
		fooService.delete(id);
	}
	
	//http://localhost:8090/foos/3
	/*@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Foo> getFooById(@PathVariable(value = "id") Long userId) {
		Optional<Foo> note = fooService.findbyId(userId);
		return note.isPresent() ? ResponseEntity.ok().body(note.get()) : ResponseEntity.notFound().build();
	}*/
	
	//http://localhost:8090/foos/listfoo
	@RequestMapping(value = "/listfoo", method = RequestMethod.GET)
	@ResponseBody	
	public List<Foo> listFoo() {
		List<Foo> list = fooService.getAll();
		return list;
		
	}
	//***********************************fin***************************************
	
/*	@RequestMapping(value = "/createfoo", method = RequestMethod.GET)
	@ResponseBody
	public static void createfoo() {
		Foo name = new Foo("Fred");
		savefoo(name);
	}*/
	
	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public Foo read(@PathVariable("id") Long id) {
	 * 
	 * return new Foo(id, "fake read"); }
	 */


/*	@RequestMapping(value = "/findbyid",method = RequestMethod.GET)
	@ResponseBody
	public Optional<Foo> findfoo(Long id) {
		Optional<Foo> res = fooService.findbyId(id);
		System.out.println("le nom associé à cet Id est:" + res.);
		return res;
	}*/
	
	@RequestMapping(value = "/param")
	@ResponseBody
	public String read(@RequestParam("toto") int tutu) {
		return "leresultatest" + tutu;

	}

}
