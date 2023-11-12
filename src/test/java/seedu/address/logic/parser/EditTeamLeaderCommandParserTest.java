package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_TEAM3;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAMLEADER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddDevToTeamCommand;
import seedu.address.logic.commands.AddTeamCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditTeamLeaderCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;
public class EditTeamLeaderCommandParserTest {
    private EditTeamLeaderCommandParser parser = new EditTeamLeaderCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name newTeamLeaderName = new Name(VALID_NAME_AMY);
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TEAMNAME_DESC_TEAM3
                + TEAMLEADER_DESC_NEW_LEADER_AMY, new EditTeamLeaderCommand(VALID_TEAM_TEAM3, newTeamLeaderName));
    }

    @Test
    public void parse_repeatedValue_failure() {
        String validExpectedTeam = TEAMNAME_DESC_TEAM3 + TEAMLEADER_DESC_NEW_LEADER_AMY;

        // multiple teamNames
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + validExpectedTeam,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TEAMNAME));

        // multiple teamLeaders
        assertParseFailure(parser, TEAMLEADER_DESC_NEW_LEADER_AMY + validExpectedTeam,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TEAMLEADER));

        assertParseFailure(parser, validExpectedTeam + TEAMLEADER_DESC_NEW_LEADER_AMY,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TEAMLEADER));
    }
    @Test
    public void parse_prefixMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamLeaderCommand.MESSAGE_USAGE);

        // missing teamName prefix
        assertParseFailure(parser, VALID_TEAM_TEAM3 + TEAMLEADER_DESC_LEADER3,
                expectedMessage);

        // missing teamLeader prefix
        assertParseFailure(parser, TEAMNAME_DESC_TEAM3 + VALID_NAME_AMY,
                expectedMessage);

    }



    @Test
    public void parser_noPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTeamLeaderCommand.MESSAGE_USAGE);
        assertParseFailure(parser, VALID_TEAM_TEAM3 + VALID_NAME_AMY,
                expectedMessage);
    }
}
