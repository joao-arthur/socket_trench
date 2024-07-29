use relm4::adw::prelude::*;
use relm4::prelude::*;

pub struct IdleServerModel;

#[derive(Debug)]
pub enum IdleServerOutput {
    GoToMatch,
}

#[relm4::component(pub)]
impl SimpleComponent for IdleServerModel {
    type Init = ();
    type Input = ();
    type Output = IdleServerOutput;

    view! {
        #[root]
        gtk::Box {
            set_orientation: gtk::Orientation::Vertical,
            set_hexpand: true,
            set_margin_all: 32,

            gtk::Label {
                set_label: "Create server",
                set_margin_all: 32,
                set_css_classes: &["title-1"],
            },
            adw::PreferencesGroup {
                adw::EntryRow {
                    set_valign: gtk::Align::Center,
                    set_title: "Port",
                    connect_changed[sender] => move |entry| {
                        sender.output(Self::Output::GoToMatch).unwrap()
                    },
                }
            },
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = IdleServerModel {};
        let widgets = view_output!();

        ComponentParts { model, widgets }
    }
}
