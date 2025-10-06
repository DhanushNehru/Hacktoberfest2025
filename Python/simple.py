# Make sure your .env file is in the root of the project
# and contains your OPENAI_API_KEY
from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()

# Check if the API key is loaded
if not os.getenv("OPENAI_API_KEY"):
    print("❌ Error: OPENAI_API_KEY not found in .env file.")
else:
    print("✅ API key loaded successfully.")

# Import necessary classes from LangChain
from langchain_openai import ChatOpenAI
# Updated import to address the deprecation warning
from langchain_community.agent_toolkits import load_tools
from langchain.agents import initialize_agent, AgentType

def run_simple_llm_call():
    """
    Demonstrates a basic, non-agentic call to an LLM.
    The LLM answers based only on its pre-trained knowledge.
    """
    print("\n--- Running Simple LLM Call ---")
    
    # Initialize the language model
    # model: The specific OpenAI model to use. "gpt-3.5-turbo" is fast and capable.
    # temperature: Controls randomness. 0 means the model will be very deterministic.
    llm = ChatOpenAI(model="gpt-3.5-turbo", temperature=0)
    
    # Define the question or "prompt"
    prompt = "Explain agentic AI in 2 sentences."
    
    # Use the .invoke() method to get a direct response from the model
    response = llm.invoke(prompt)
    
    # The response is an object; we access the text content with .content
    print(f"LLM Response:\n{response.content}")
    print("-----------------------------\n")


def run_agent_with_tools():
    """
    Demonstrates a true agent that can use tools to answer questions.
    The agent can reason and decide which tool to use based on the prompt.
    """
    print("\n--- Running Agent with Tools ---")
    
    # 1. Initialize the language model
    llm = ChatOpenAI(model="gpt-3.5-turbo", temperature=0)
    
    # 2. Load the tools the agent can use
    # "wikipedia": For looking up factual, real-world information.
    # "llm-math": A tool that uses an LLM to do mathematical calculations.
    tools = load_tools(["wikipedia", "llm-math"], llm=llm)
    
    # 3. Initialize the agent
    # tools: The list of tools the agent has access to.
    # llm: The language model that powers the agent's reasoning.
    # agent: The type of agent. ZERO_SHOT_REACT_DESCRIPTION uses the ReAct framework
    #        to decide which tool to use based on the tool's description.
    # verbose=True: This is crucial for learning! It prints out the agent's
    #               step-by-step thought process.
    agent = initialize_agent(
        tools,
        llm,
        agent=AgentType.ZERO_SHOT_REACT_DESCRIPTION,
        verbose=True
    )
    
    # 4. Run the agent with a query that requires both tools
    prompt = "Who is the CEO of Tesla and what is the result of 123 multiplied by 45?"
    
    # We use agent.invoke() which returns a dictionary. The final answer is in the 'output' key.
    result = agent.invoke({"input": prompt})
    
    print("\nFinal Answer:")
    print(result['output'])
    print("------------------------------\n")


if __name__ == "__main__":
    # This block runs when the script is executed directly
    run_simple_llm_call()
    run_agent_with_tools()
