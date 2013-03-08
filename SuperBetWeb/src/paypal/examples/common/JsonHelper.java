package paypal.examples.common;

/****************************************************************
THIS IS STRICTLY EXAMPLE SOURCE CODE. IT IS ONLY MEANT TO 
QUICKLY DEMONSTRATE THE CONCEPT AND THE USAGE OF DIFFERENT APIS
THAT USES ITS METHODS. PLEASE NOTE THAT THIS IS *NOT* PRODUCTION-QUALITY 
CODE AND SHOULD NOT BE USED AS SUCH.

THIS EXAMPLE CODE IS PROVIDED TO YOU ONLY ON AN "AS IS" 
BASIS WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, EITHER 
EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION ANY WARRANTIES 
OR CONDITIONS OF TITLE, NON-INFRINGEMENT, MERCHANTABILITY OR 
FITNESS FOR A PARTICULAR PURPOSE. PAYPAL MAKES NO WARRANTY THAT 
THE SOFTWARE OR DOCUMENTATION WILL BE ERROR-FREE. IN NO EVENT 
SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY 
DIRECT, INDIRECT, INCIDENTAL, SPECIAL,  EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT 
OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF 
THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY 
OF SUCH DAMAGE.
****************************************************************/


/***	This helper class is used to construct StringBuffers of elements in a JSON style request body.
		Reference: http://www.json.org
		
		Requirements:
		1. Numbers, booleans, and null are input as strings.
		2. In a partially filled array, null values all come in the end instead of in the middle.

		Note:
		When constructing a JSON style request body, start from the low level elements.
		For example, to construct an array of objects, construct the object StringBuffers
		first, then construct the array StringBuffer based on that.
*/

public class JsonHelper {
	//Construct a name value pair like "name":"stringValue"
	static public final StringBuffer stringNvp(String name, String value) {
		StringBuffer out = new StringBuffer();
		out.append("\"");
		out.append(name);
		out.append("\"");
		out.append(":");
		out.append("\"");
		out.append(value);
		out.append("\"");
		return out;
	}

	//Construct a name value pair like "name":nonStringValue
	//where nonStringValue can be number or true/false/null.
	static public final StringBuffer nonStringNvp(String name, String value) {
		StringBuffer out = new StringBuffer();
		out.append("\"");
		out.append(name);
		out.append("\"");
		out.append(":");
		out.append(value);
		return out;
	}

	//Construct a name value pair in which the value is an object like {"name":"value"}
	//or array like ["string1", "string2"].
	static public final StringBuffer bufferNvp(String name, StringBuffer value) {
		StringBuffer out = new StringBuffer();
		out.append("\"");
		out.append(name);
		out.append("\"");
		out.append(":");
		out.append(value);
		return out;
	}

	//Construct a string array like ["string1", "string2"].
	static public final StringBuffer stringArray(String[] values) {
		StringBuffer out = new StringBuffer();
		out.append("[");
		if (values.length > 0) {
			out.append("\"");
			out.append(values[0]);
			out.append("\"");
			for (int i=1; i< values.length; i++) {
				if (values[i] != null) {
					out.append(",");
					out.append(values[i]);
				}
			}
		}
		out.append("]");
		return out;
	}

	//Construct a non-string array like [10.00, 20.00, 30.10]
	//Note that values are input as java strings.
	static public final StringBuffer nonStringArray(String[] values) {
		StringBuffer out = new StringBuffer();
		out.append("[");
		if (values.length > 0) {
			out.append(values[0]);
			for (int i=1; i< values.length; i++) {
				if (values[i] != null) {
					out.append(",");
					out.append(values[i]);
				}
			}
		}
		out.append("]");
		return out;
	}


	//Construct an array of objects or arrays.
	// The objects or arrays should have been converted to StringBuffers before this method is called.
	static public final StringBuffer bufferArray(StringBuffer[] values) {
		StringBuffer out = new StringBuffer();
		out.append("[");
		if (values.length > 0) {
			out.append(values[0]);
			for (int i=1; i< values.length; i++) {
				if (values[i] != null) {
					out.append(",");
					out.append(values[i]);
				}
			}
		}
		out.append("]");
		return out;
	}
}
