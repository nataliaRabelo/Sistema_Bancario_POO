package br.winxbank.repository;

import br.winxbank.sistemabancario.*;
import br.winxbank.sistemaclientes.Cliente;
import br.winxbank.sistemaclientes.RegistroDeClientes;

import java.io.*;
import java.util.ArrayList;

import com.google.gson.*;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

/**
 * @author Natália
 * Classe responsável por persistir dados de clientes em um arquivo.
 */
public class ArquivoDeClientes {

    private static ArquivoDeClientes instancia;

    public void readjason() throws IOException {
        JsonElement json = null;
        ArrayList<Cliente> clientes  = new ArrayList<>();
        try (Reader reader = new InputStreamReader(new FileInputStream("clientes.json"), "UTF-8")) {
            json = JsonParser.parseReader(reader);
            //System.out.println(json);
            JSONArray array = new JSONArray(json.toString());
            for(int i=0; i < array.length(); i++)
            {
                JSONObject object = array.getJSONObject(i);
                Cliente cliente = new Cliente(object.getString("nome"), object.getString("cpf"));
                //System.out.println("Cliente criado: "+ cliente.getNome() + cliente.getCpf()); //print debug
                JSONArray array2;
                //System.out.println("Array de contas do cliente atual: " + object.getJSONArray("contas")); //print debug
                array2 = object.getJSONArray("contas");
                for(int j = 0; j < array2.length(); j++){
                    JSONObject object2 = array2.getJSONObject(j);
                    //System.out.println(object2); //print debug
                    //System.out.println(object2.getString("numeroConta")); //print debug
                    JSONArray array3;
                    //System.out.println("Extrato da conta: " + object2.getJSONArray("extrato")); //print debug
                    JSONObject cartaoCreditoJson;
                    Conta contaAtual;
                    try {
                        cartaoCreditoJson = object2.getJSONObject("cartaoCredito");
                        //System.out.println(cartaoCreditoJson); //print debug
                        JSONObject cartaoDebitoJson;
                        cartaoDebitoJson = object2.getJSONObject("cartao");
                        //System.out.println(cartaoDebitoJson);
                        array3 = object2.getJSONArray("extrato");
                        contaAtual = new ContaCorrente(object2.getInt("numeroConta"), object2.getDouble("saldo"), new Cartao(cartaoDebitoJson.getInt("numero"), cartaoDebitoJson.getInt("csv")), object2.getDouble("dividaDeEmprestimo"), new CartaoCredito(cartaoCreditoJson.getDouble("fatura"), cartaoCreditoJson.getInt("indexMesDaFatura"), cartaoCreditoJson.getBoolean("faturaPaga"), cartaoCreditoJson.getDouble("limite"), cartaoCreditoJson.getInt("numero"), cartaoCreditoJson.getInt("csv")));
                        //System.out.println("Conta corrente foi criada"); //print debug

                    }catch (JSONException e){
                        JSONObject cartaoDebitoJson;
                        cartaoDebitoJson = object2.getJSONObject("cartao");
                        //System.out.println(cartaoDebitoJson);
                        array3 = object2.getJSONArray("extrato");
                        contaAtual = new ContaPoupanca(object2.getInt("numeroConta"), object2.getDouble("saldo"), new Cartao(cartaoDebitoJson.getInt("numero"), cartaoDebitoJson.getInt("csv")), object2.getDouble("dividaDeEmprestimo"));
                        //System.out.println("Conta poupanca foi criada"); //print debug
                    }
                    for(int x = 0; x < array3.length(); x++){
                        JSONObject object3 = array3.getJSONObject(x);
                        //System.out.println(object3.getString("mesAtual"));
                        Movimentacao movimentacao = new Movimentacao(object3.getString("mesAtual"), object3.getDouble("dinheiroMovimentado"), object3.getString("tipoDaMovimentacao"));
                        contaAtual.setExtrato(movimentacao);
                        cliente.setContas(contaAtual);
                    }

                }
                clientes.add(cliente);
            }
            RegistroDeClientes.getInstancia().setClientes(clientes);

            //for(Cliente cliente : clientes){
                //System.out.println(cliente);
            //}
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void escreverJson(ArrayList<Cliente> clientes) throws IOException {
        try (Writer writer = new FileWriter("clientes.json")) {
            JSONArray jsonArray = new JSONArray();
            for(Cliente cliente : clientes){
                JSONObject cliente2 = new JSONObject();
                cliente2.put("nome", cliente.getNome());
                cliente2.put("cpf", cliente.getCpf());
                JSONArray contas = new JSONArray(cliente.getContas());
                cliente2.put("contas", contas);
                jsonArray.put(cliente2);
            }
            writer.write(jsonArray.toString());
        }
    }
    /**
     * Singleton que só permite uma instância do objeto ser criada, quando o atributo estático instancia tem o valor nulo.
     * @return
     */
    public static ArquivoDeClientes getInstancia() {
        if (instancia == null) {
            instancia = new ArquivoDeClientes();
        }
        return instancia;
    }

}
